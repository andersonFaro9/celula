package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import io.realm.Realm
import kotlinx.android.synthetic.main.activity_update.*

class EditarActivity : AppCompatActivity() {

    val realm by lazy {
        Realm.getDefaultInstance()
    }

    val id by lazy {
        intent.getStringExtra("id")
    }

     var celulaModel: CelulaModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_update)

        celulaModel = realm.where(CelulaModel::class.java).equalTo("id", this.id).findFirst()

        novoTitulo.setText(celulaModel?.titulo)
        novoDetalhe.setText(celulaModel?.detalhes)

        valida()

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

    fun valida() {
        editar?.setOnClickListener {
             var view: View? = null

            novoTitulo.text.isEmpty()
            if (!novoTitulo.text.isEmpty() && !novoDetalhe.text.isEmpty()) {

                realm.executeTransaction {
                    this.celulaModel?.titulo = novoTitulo.text.toString()
                    this.celulaModel?.detalhes = novoDetalhe.text.toString()
                    startActivity(Intent(this, MainActivity::class.java))
                }

            } else {
                val snack = Snackbar.make(it,"Preencha os dados",Snackbar.LENGTH_LONG)
                snack.show()

            }
        }
    }


}