package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class UpdateActivity : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var spacecrafts: List<Model>
    internal var adapter: NotaAdapter? = null
    internal var rv: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar

        setSupportActionBar(toolbar)

        val layoutManager = GridLayoutManager(this, 2)
        rv?.layoutManager = layoutManager


        realm = Realm.getDefaultInstance()

        //RETRIEVE
        val helper = Crud(realm)
        spacecrafts = helper.recupera()

        //BIND
        adapter = NotaAdapter(this, spacecrafts)
        rv?.adapter = adapter


        fab?.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }
    }



}