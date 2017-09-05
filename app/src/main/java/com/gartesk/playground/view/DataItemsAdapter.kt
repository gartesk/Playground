package com.gartesk.playground.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.gartesk.playground.R
import com.gartesk.playground.domain.model.DataItem

class DataItemsAdapter
    : RecyclerView.Adapter<DataItemViewHolder>() {

    var dataItems: List<DataItem>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = dataItems?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataItemViewHolder {
        val itemView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_data_item, parent, false)
        return DataItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataItemViewHolder?, position: Int) {
        val dataItem: DataItem? = dataItems?.get(position)
        holder?.nameTextView?.text = dataItem?.name
        holder?.countTextView?.text = dataItem?.count.toString()
    }
}