package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm

import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var modelList: List<Model>
    internal var notaAdapter: NotaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val layoutManager = GridLayoutManager(this, 2)
        rv?.layoutManager = layoutManager

        realm = Realm.getDefaultInstance()

        val crud = Crud(realm)
        modelList = crud.recupera()

        notaAdapter = NotaAdapter(this, modelList)
        rv?.adapter = notaAdapter

        adicionar()
    }


    fun adicionar() {
        fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

    }
}