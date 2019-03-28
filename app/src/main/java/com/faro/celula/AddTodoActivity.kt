package com.faro.celula

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*


class AddTodoActivity : AppCompatActivity() {
    val realm by lazy { Realm.getDefaultInstance() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add)

        supportActionBar?.elevation = 0F

        show()
        salvaDados()

    }




    fun show () {
        notaEd.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                saveBtn.visibility = View.INVISIBLE
            }

            override fun afterTextChanged(s: Editable?) {

                saveBtn.visibility = View.VISIBLE
                saveBtn.setTextColor(Color.parseColor("#297AE0"))

                when {
                    notaEd.text.isEmpty() -> {
                        saveBtn.visibility = View.INVISIBLE
                    }

                    else -> salvaDados()
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

        })


        detalhesEd.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                saveBtn.visibility = View.INVISIBLE
            }

            override fun afterTextChanged(s: Editable?) {

                saveBtn.visibility = View.VISIBLE
                saveBtn.setTextColor(Color.parseColor("#297AE0"))

                when {
                    detalhesEd.text.isEmpty() -> {
                        saveBtn.visibility = View.INVISIBLE
                    }

                    else -> salvaDados()
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

        })




    }

    private fun salvaDados() {

        saveBtn.setOnClickListener {

            if (!notaEd.text.isEmpty() && !detalhesEd.text.isEmpty()) {

                this.realm.executeTransaction {
                    val todo = this.realm.createObject(Nota::class.java, UUID.randomUUID().toString())

                    todo.nota = notaEd.text.toString()
                    todo.detalhes = detalhesEd.text.toString()

                    startActivity(Intent(this, MainActivity::class.java))
                }

            } else {
                Toast.makeText(this, "erro", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when {

            item.itemId == R.id.add -> {
                finish()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

}