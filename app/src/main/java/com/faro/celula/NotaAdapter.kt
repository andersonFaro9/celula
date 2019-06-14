package com.faro.celula

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class NotaAdapter(todos: ArrayList<Nota>) : RecyclerView.Adapter<NotaViewHolder>() {

    var items: ArrayList<Nota> = todos

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NotaViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return NotaViewHolder(layoutInflater.inflate(R.layout.cardview_list_item, viewGroup, false))

    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(notaViewHolder: NotaViewHolder, position: Int) {
        notaViewHolder.bind(this.items[position])

    }

}