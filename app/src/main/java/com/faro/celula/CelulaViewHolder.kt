package com.faro.celula

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import io.realm.Realm

class CelulaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titulo: TextView = itemView.findViewById(R.id.titulo)
    val detalhes: TextView = itemView.findViewById(R.id.detalhes)

    val deleta: ImageButton = itemView.findViewById(R.id.deleta)
    val context: Context = itemView.context

    fun bind(model: Nota) {

        val titleText: String = model.nota.toString()
        val detalhesText: String = model.detalhes.toString()

        titulo.text = titleText
        detalhes.text = detalhesText

        deleta.setOnClickListener {
            val realm = Realm.getDefaultInstance()

            AlertDialog.Builder(this.context)

                .setMessage("Excluir?")

                .setPositiveButton("Sim") { dialogInterface, i ->

                    realm.executeTransaction {
                        realm.where(Nota::class.java).equalTo("id", model.id).findFirst()?.deleteFromRealm()

                        itemView.context.startActivity(Intent(itemView.context, MainActivity::class.java))


                    }

                }.setNegativeButton("Cancelar", null).create().show()
        }

        itemView.setOnClickListener {
            val intent = Intent(context, EditarActivity::class.java)

            intent.putExtra("id", model.id)
            context.startActivity(intent)

        }

    }
}

