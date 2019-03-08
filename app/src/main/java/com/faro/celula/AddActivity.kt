package com.faro.celula

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_add.*
import java.util.ArrayList

class AddActivity : AppCompatActivity() {

    var realm: Realm? = null
    lateinit var spacecrafts: List<TesteModel>
    internal var adapter: MyAdapter? = null
    internal var rv: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        realm = Realm.getDefaultInstance()

        //RETRIEVE
        val helper = RealmHelper(realm)
        spacecrafts = helper.retrieve()

        displayInputDialog()
        cancela()

    }

    private fun displayInputDialog() {

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when  {

//            item.itemId == R.id.fechar -> {
//
//                return true
//            }

        }



        return super.onOptionsItemSelected(item)
    }

    fun cancela() = cancela.setOnClickListener {    finish()    }



    fun salvaDados() {
        saveBtn?.setOnClickListener {

            when {
                !notaEd.text.isEmpty() and !detalhesEd.text.isEmpty() -> {

                    val s = Spacecraft()
                    s.nota = notaEd?.text.toString()
                    s.detalhes = detalhesEd?.text.toString()

                    val helper = RealmHelper(realm)
                    helper.save(s)

                    notaEd?.setText("")
                    detalhesEd?.setText("")

                    spacecrafts = helper.retrieve()
                    adapter = MyAdapter(this@AddActivity, spacecrafts)
                    rv?.adapter = adapter


                    startActivity(Intent(this, MainActivity::class.java))
                }
                   //Se quiser manter o texto que acabou de ser digitado -> nameEditTxt?.setText(s.nota)
            }
        }
    }
}
