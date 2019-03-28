package com.faro.celula

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_main.*

class AddActivity : AppCompatActivity() {

    var realm: Realm? = null
    lateinit var modelList: List<Model>
    internal var adapter: NotaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setSupportActionBar(toolbar)

        realm = Realm.getDefaultInstance()

        val crud = Crud(realm)

        modelList = crud.recupera()

        valida()

        cancela()

    }

    private fun valida() {

        notaEd.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                saveBtn.isEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {

                saveBtn.isEnabled = true
                cancela.visibility = View.VISIBLE
                saveBtn.setTextColor(Color.parseColor("#297AE0"))

                when {
                    notaEd.text.isEmpty() -> {
                        saveBtn.setTextColor(Color.parseColor("#8B8A8A"))
                        cancela.visibility = View.INVISIBLE
                    }
                    else -> salvaDados()
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })



        detalhesEd.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                saveBtn.isEnabled = false
            }

            override fun afterTextChanged(s: Editable?) {

                saveBtn.isEnabled = true
                cancela.visibility = View.VISIBLE
                saveBtn.setTextColor(Color.parseColor("#297AE0"))

                when {
                    detalhesEd.text.isEmpty() -> {
                        saveBtn.setTextColor(Color.parseColor("#8B8A8A"))
                        cancela.visibility = View.INVISIBLE
                    }
                    else -> salvaDados()
                }

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

    }

    fun cancela() {
        cancela.setOnClickListener {
            finish()
        }
    }

    fun salvaDados() {
        saveBtn?.setOnClickListener  {

            when {
                !notaEd.text.isEmpty() and !detalhesEd.text.isEmpty() -> {

                    //Salva
                    val nota = Nota()
                    nota.nota = notaEd?.text.toString()
                    nota.detalhes = detalhesEd?.text.toString()

                    val crud = Crud(realm)
                    crud.salva(nota)

                    notaEd?.setText("")
                    detalhesEd?.setText("")

                    //Recupera
                    modelList = crud.recupera()

                    adapter = NotaAdapter(this@AddActivity, modelList)

                    rv?.adapter = adapter

                    startActivity(Intent(this, MainActivity::class.java))
                }

            }
        }
    }
}