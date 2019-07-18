package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), OnDeleteListener {

    val realm by lazy {
        Realm.getDefaultInstance()
    }

    lateinit var adapter: NotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_main)

        adicionarDados()

        val todos: ArrayList<Nota> = this.getTodos()
        adapter = NotaAdapter(todos)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        deixarRecycleViewInvisivel(todos)


    }

    private fun adicionarDados() {
        adicionar.setOnClickListener {

            startActivity(Intent(this, AdicionaDadosActivity::class.java))
        }
    }

    fun deixarRecycleViewInvisivel(celulaModel: ArrayList<Nota>) {

        when {
            !celulaModel.isEmpty() -> {
                recyclerView.visibility = View.VISIBLE;
                texto_vazio.visibility = View.GONE;
                celula.visibility = View.GONE

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when {

            item.itemId == R.id.calendario -> {
                val i = Intent(AlarmClock.ACTION_SET_ALARM)
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Célula")
                Toast.makeText(this,"Agende sua data e hora da célula", Toast.LENGTH_LONG).show()

                startActivity(i)

                return true
            }


            item.itemId == R.id.relatorio -> {
                val i = Intent(this, RelatorioActivity::class.java)
                startActivity(i)

                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun getTodos(): ArrayList<Nota> {
        val arraylist = ArrayList(this.realm.where(Nota::class.java).findAll())
        return arraylist
    }


    override fun setOnDeleteListener() {

        this.adapter.items = getTodos()

        this.adapter.notifyDataSetChanged()
    }
}