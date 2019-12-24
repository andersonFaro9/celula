package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

import androidx.recyclerview.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import io.realm.RealmConfiguration
import java.io.FileNotFoundException
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService


class MainActivity : AppCompatActivity(), OnDeleteListener {


    lateinit var adapter: NotaAdapter

      val realm by lazy{
        Realm.getDefaultInstance()
      }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_main)

        adicionarDados()

        val todos: ArrayList<NotaBd> = this.getTodos()
        adapter = NotaAdapter(todos)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        deixarRecycleViewInvisivel(todos)


    }

    private fun getTodos(): ArrayList<NotaBd> {



        val arraylist = ArrayList(this.realm.where(NotaBd::class.java).findAll())
        return arraylist
    }

    private fun adicionarDados() {
        adicionar.setOnClickListener {

            startActivity(Intent(this, AdicionaDadosActivity::class.java))
        }
    }

    fun deixarRecycleViewInvisivel(celulaModel: ArrayList<NotaBd>) {

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
                Toast.makeText(this, "Agende sua data e hora da célula", Toast.LENGTH_LONG).show()

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


    override fun setOnDeleteListener() {

        this.adapter.items = getTodos()

        this.adapter.notifyDataSetChanged()
    }
}