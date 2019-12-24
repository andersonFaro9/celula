package com.faro.celula

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import io.realm.Realm
import kotlinx.android.synthetic.main.activity_update.*

class EditarActivity : AppCompatActivity() {

    val realm by lazy {
        Realm.getDefaultInstance()
    }

    val id by lazy {
        intent.getStringExtra("id")
    }

    var celulaModel: NotaBd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_update)

        celulaModel = realm.where(NotaBd::class.java).equalTo("id", this.id).findFirst()


        novoTitulo.setText(celulaModel?.assunto)
        novoDetalhe.setText(celulaModel?.texto)

        valida()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.editar_main, menu)
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
                    this.celulaModel?.assunto = novoTitulo.text.toString()
                    this.celulaModel?.texto = novoDetalhe.text.toString()
                    startActivity(Intent(this, MainActivity::class.java))
                }

            } else {
                Toast.makeText(this, "Preencha os campos", Toast.LENGTH_LONG).show()


            }
        }
    }


}
