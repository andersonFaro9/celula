package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.Toast
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity: AppCompatActivity() {

    val realm by lazy { Realm.getDefaultInstance() }

    val id by lazy { intent.getStringExtra("id") }

    lateinit var todo: Nota

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_update)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Edit Todo"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        }

        todo = realm.where(Nota::class.java).equalTo("id", this.id).findFirst()!!

        titleInput.setText(todo.nota.toString())
        descriptionInput.setText(todo.detalhes.toString())

        enviar.setOnClickListener {
            updateTodo()
            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun updateTodo () {
        realm.executeTransaction {
            this.todo.nota = titleInput.text.toString()
            this.todo.detalhes = descriptionInput.text.toString()
        }
    }
}