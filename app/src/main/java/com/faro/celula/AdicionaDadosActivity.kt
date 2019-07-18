package com.faro.celula

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*


class AdicionaDadosActivity : AppCompatActivity() {

    val realm by lazy { Realm.getDefaultInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add)

        supportActionBar?.elevation = 0F

        validaDados()
        salvaDados()

    }

    fun validaDados() {

        detalhes.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                salva.visibility = View.INVISIBLE
            }

            override fun afterTextChanged(s: Editable?) {

                salva.visibility = View.VISIBLE
                salva.setTextColor(Color.parseColor("#297AE0"))

                when {
                    detalhes.text.isEmpty() -> {
                        salva.visibility = View.INVISIBLE
                    }

                    else -> salvaDados()
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

    }

    private fun salvaDados() {

        salva.setOnClickListener {

            if (!titulo.text.isEmpty() && !detalhes.text.isEmpty()) {

                this.realm.executeTransaction {
                    val todo = this.realm.createObject(Nota::class.java, UUID.randomUUID().toString())

                    todo.nota = titulo.text.toString()
                    todo.detalhes = detalhes.text.toString()

                    startActivity(Intent(this, MainActivity::class.java))

                }

            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when {

            item.itemId == R.id.fechar -> {
                finish()
                return true
            }



        }

        return super.onOptionsItemSelected(item)
    }

}