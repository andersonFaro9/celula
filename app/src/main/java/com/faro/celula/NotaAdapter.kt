package com.faro.celula

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NotaAdapter(internal var context: Context, internal var modelList: List<Model>)
            : RecyclerView.Adapter<NotaAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.model, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val model: Model = modelList.get(position)
        holder.notaTxt.text = model.notaModel
        holder.detalhesTxt.text = model.detalhesModel

    }

    override fun getItemCount(): Int = modelList.size

    inner class MyViewHolder(itemView: View) : View.OnClickListener, RecyclerView.ViewHolder(itemView) {

        internal var notaTxt: TextView = itemView.findViewById<View>(R.id.notaTxt) as TextView
        internal var detalhesTxt: TextView = itemView.findViewById<View>(R.id.detalhes) as TextView

        init { itemView.setOnClickListener(this)  }

        override fun onClick(v: View?) {

            val intent = Intent(itemView.context, UpdateActivity::class.java)
            val model: List<Nota>? = null

            intent.putExtra("key_nota", model?.get(adapterPosition)?.nota )
            intent.putExtra("key_detalhes", model?.get(adapterPosition)?.detalhes )

            itemView.context.startActivity(intent)
        }

    }
}