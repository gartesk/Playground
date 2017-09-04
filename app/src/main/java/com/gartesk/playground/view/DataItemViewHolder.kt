package com.gartesk.playground.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.gartesk.playground.R

class DataItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val nameTextView: TextView = itemView.findViewById(R.id.text_view_item_name)
    var countTextView: TextView = itemView.findViewById(R.id.text_view_item_count)
}