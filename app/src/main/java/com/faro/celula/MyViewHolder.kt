package com.faro.celula

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var notaTxt: TextView = itemView.findViewById<View>(R.id.notaTxt) as TextView
    internal var detalhesTxt: TextView = itemView.findViewById<View>(R.id.detalhes) as TextView


}
