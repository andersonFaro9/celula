package com.faro.celula

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    val realm by lazy {
        Realm.getDefaultInstance()
    }

    val id by lazy {
        intent.getStringExtra("id")
    }

     var todo: Nota? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        setContentView(R.layout.activity_update)

        todo = realm.where(Nota::class.java).equalTo("id", this.id).findFirst()

        titleInput.setText("")
        descriptionInput.setText("")

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

            titleInput.text.isEmpty()
            if (!titleInput.text.isEmpty() && !descriptionInput.text.isEmpty()) {

                realm.executeTransaction {
                    this.todo?.nota = titleInput.text.toString()
                    this.todo?.detalhes = descriptionInput.text.toString()
                    startActivity(Intent(this, MainActivity::class.java))
                }

            } else {
                val snack = Snackbar.make(it,"Preencha os dados",Snackbar.LENGTH_LONG)
                snack.show()

            }
        }
    }


}