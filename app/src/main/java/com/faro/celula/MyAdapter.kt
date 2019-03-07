package com.faro.celula

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

class MyAdapter(internal var c: Context, internal var spacecrafts: ArrayList<String>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.model, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTxt.text = spacecrafts[position]

    }

    override fun getItemCount(): Int {
        return spacecrafts.size
    }
}
