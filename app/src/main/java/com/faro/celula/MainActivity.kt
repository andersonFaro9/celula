package com.faro.celula

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import java.util.ArrayList

import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var realm: Realm
    lateinit var spacecrafts: ArrayList<String>
    internal var adapter: MyAdapter? = null
    internal var rv: RecyclerView ? = null

    var nameEditTxt: EditText? = null
    var saveBtn: Button? = null

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

            displayInputDialog()
        }
    }

    fun show() {
        saveBtn?.isEnabled = true
    }
    //SHOW DIALOG
    private fun displayInputDialog() {
        val d = Dialog(this)
        d.setTitle("Save To Realm")
        d.setContentView(R.layout.input_dialog)

        nameEditTxt = d.findViewById(R.id.nameEditText)

        nameEditTxt?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                show()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })
        saveBtn = d.findViewById(R.id.saveBtn)

        saveBtn?.setOnClickListener {
            //GET DATA
            val s = Spacecraft()
            s.name = nameEditTxt?.text.toString()

                val helper = RealmHelper(realm)
                helper.save(s)
                nameEditTxt?.setText("")

                spacecrafts = helper.retrieve()
                adapter = MyAdapter(this@MainActivity, spacecrafts)
                rv?.adapter = adapter

                //Toast.makeText(this,"teste", Toast.LENGTH_SHORT).show()

            //Se quiser manter o texto que acabou de ser digitado -> nameEditTxt?.setText(s.name)

        }


        d.show()


    }





}