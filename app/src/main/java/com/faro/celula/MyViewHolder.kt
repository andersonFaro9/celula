package com.faro.celula

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var nameTxt: TextView

    init {

        nameTxt = itemView.findViewById<View>(R.id.nameTxt) as TextView
    }
}
