package com.faro.celula

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MyAdapter(internal var c: Context, internal var spacecrafts: List<TesteModel>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.model, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val testeModel: TesteModel = spacecrafts.get(position)
        holder.notaTxt.text = testeModel.notaModel
        holder.detalhesTxt.text = testeModel.detalhesModel

    }

    override fun getItemCount(): Int {
        return spacecrafts.size
    }
}
