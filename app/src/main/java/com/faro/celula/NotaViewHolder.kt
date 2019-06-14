package com.faro.celula

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import io.realm.Realm

class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titulo: TextView = itemView.findViewById(R.id.titulo)
    val detalhes: TextView = itemView.findViewById(R.id.detalhes)

    //val editaNota: ImageButton = itemView.findViewById(R.id.editaNota)

    val deleteNota: ImageButton = itemView.findViewById(R.id.deleteNota)
    val context: Context = itemView.context

    fun bind(model: Nota) {

        val titleText: String = model.nota.toString()
        val descriptionText: String = model.detalhes.toString()

        titulo.text = titleText
        detalhes.text = descriptionText


        deleteNota.setOnClickListener {
            val realm = Realm.getDefaultInstance()

            AlertDialog.Builder(this.context)

                .setMessage("Deseja excluir?")

                .setPositiveButton("Sim") { dialogInterface, i ->

                    realm.executeTransaction {
                        realm.where(Nota::class.java).equalTo("id", model.id).findFirst()?.deleteFromRealm()

                        itemView.context.startActivity(Intent(itemView.context, MainActivity::class.java))

                    }

                }.setNegativeButton("Não", null).create().show()
        }

        itemView.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)

            intent.putExtra("id", model.id)
            context.startActivity(intent)

        }

    }
}

