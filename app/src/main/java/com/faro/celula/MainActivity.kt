package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnDeleteListener {

    val realm by lazy {
        Realm.getDefaultInstance()

    }

    lateinit var adapter: NotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_main)

        fab.setOnClickListener {

            addTodo()
        }

        val todos: ArrayList<Nota> = this.getTodos()
        adapter = NotaAdapter(todos)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when  {

            item.itemId == R.id.action_bytitle -> {
               // startActivity(Intent(this, AutoEscolaFrancaEnderecoActivity::class.java))
                return true
            }


        }

        return super.onOptionsItemSelected(item)
    }

    private fun addTodo () {

        startActivity(Intent(this, AddTodoActivity::class.java))

    }


    private fun getTodos (): ArrayList<Nota> = ArrayList(this.realm.where(Nota::class.java).findAll())


    override fun setOnDeleteListener() {

        this.adapter.items = getTodos()

        this.adapter.notifyDataSetChanged()
    }
}