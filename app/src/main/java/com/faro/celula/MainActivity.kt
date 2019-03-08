package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

import java.util.ArrayList

import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var spacecrafts: List<TesteModel>
    internal var adapter: MyAdapter? = null
    internal var rv: RecyclerView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //SETUP RECYCLERVIEW
        rv = findViewById<View>(R.id.rv) as RecyclerView

        val layoutManager = GridLayoutManager(this, 2)
        rv?.layoutManager = layoutManager
        //SETUP REEALM

        realm = Realm.getDefaultInstance()

        //RETRIEVE
        val helper = RealmHelper(realm)
        spacecrafts = helper.retrieve()

        //BIND
        adapter = MyAdapter(this, spacecrafts)
        rv?.adapter = adapter


        fab.setOnClickListener {

            startActivity(Intent(this,AddActivity::class.java))
            overridePendingTransition(R.anim.right, R.anim.left);
            //displayInputDialog()
        }
    }



}