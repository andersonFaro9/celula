package com.faro.celula

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import io.realm.Realm

class CelulaViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    val titulo: TextView = itemView.findViewById(R.id.titulo)
    val detalhes: TextView = itemView.findViewById(R.id.detalhes)

    val deleta: ImageButton = itemView.findViewById(R.id.deleta)
    val context: Context = itemView.context

    fun bind(model: NotaBd) {

        val titleText: String = model.assunto.toString()
        val detalhesText: String = model.texto.toString()

        titulo.text = titleText
        detalhes.text = detalhesText

        deleta.setOnClickListener {
            val realm = Realm.getDefaultInstance()

            AlertDialog.Builder(this.context)

                .setMessage("Excluir?")

                .setPositiveButton("Sim") { dialogInterface, i ->

                    realm.executeTransaction {
                        realm.where(NotaBd::class.java).equalTo("id", model.id).findFirst()?.deleteFromRealm()

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

