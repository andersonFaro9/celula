package com.faro.celula

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

import androidx.recyclerview.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_usar.*
import java.util.*


class ComoUsarActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F
        supportActionBar?.title = "Como fazer seu relatório"

        setContentView(R.layout.activity_usar)
        show()
        Toast.makeText(this, "Como fazer seu relatório",Toast.LENGTH_SHORT).show()

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.comousar_main, menu)
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

    fun show () {
        val listaModel: ArrayList<ListaModel> = ArrayList()

        listaModel.add(ListaModel("Primeiro passo", "Vá em configurações do celular" , 0, 0))
        listaModel.add(ListaModel("Segundo passo", "Vá em aplicativos" , 0, 0))
        listaModel.add(ListaModel("Terceiro passo", "Procure pelo notaCelula" , 0, 0))
        listaModel.add(ListaModel("Quarto passo", "Em permissões clique para permitir " , 0, 0))
        listaModel.add(ListaModel("Quinto passo", "Depois de enviar, o relatório vai para a memória do cel " , 0, 0))

        listView.adapter = ListaImagemTextoSimplesAdapter(applicationContext, listaModel)


    }





}