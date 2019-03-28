package com.faro.celula

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import io.realm.Realm




class NotaAdapter(todos: ArrayList<Nota>) : RecyclerView.Adapter<NotaAdapter.TodoViewHolder>() {

    var items: ArrayList<Nota> = todos

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        return TodoViewHolder(layoutInflater.inflate(R.layout.todo_list_item, p0, false))
    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(todoViewHolder: TodoViewHolder, position: Int) {
        todoViewHolder.bind(this.items[position], position)

    }
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTv: TextView = itemView.findViewById(R.id.titulo)
        val descriptionTv: TextView = itemView.findViewById(R.id.descricao)

        val editButton: ImageButton = itemView.findViewById(R.id.editButton)

        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val context: Context = itemView.context

        fun bind(model: Nota, position: Int) {

            val titleText: String = model.nota.toString()
            val descriptionText: String = model.detalhes.toString()

            titleTv.text = titleText
            descriptionTv.text = descriptionText

            editButton.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("id", model.id)
                context.startActivity(intent)
            }

            deleteButton.setOnClickListener {
                val realm = Realm.getDefaultInstance()

                AlertDialog.Builder(this.context)

                    .setMessage("Tem certeza que quer deletar?")

                    .setPositiveButton("Sim") { dialogInterface, i ->

                        realm.executeTransaction {
                            realm.where(Nota::class.java).equalTo("id", model.id).findFirst()?.deleteFromRealm()

                            if (this.context is OnDeleteListener) {

                                this.context.setOnDeleteListener()
                            }
                        }
                    }.setNegativeButton("Não", null).create().show()
            }

        }
    }
}