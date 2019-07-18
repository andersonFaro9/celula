package com.faro.celula

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class NotaAdapter(todos: ArrayList<CelulaModel>) : RecyclerView.Adapter<CelulaViewHolder>() {

    var items: ArrayList<CelulaModel> = todos

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CelulaViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return CelulaViewHolder(layoutInflater.inflate(R.layout.cardview_list_item, viewGroup, false))

    }

    override fun getItemCount(): Int = this.items.size

    override fun onBindViewHolder(celulaViewHolder: CelulaViewHolder, position: Int) {
        celulaViewHolder.bind(this.items[position])

    }

}