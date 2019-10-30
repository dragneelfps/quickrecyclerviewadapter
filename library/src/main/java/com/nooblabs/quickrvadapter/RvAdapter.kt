package com.nooblabs.quickrvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class RvAdapter<T>(@LayoutRes private val layout: Int, private val adapterDsl: AdapterDsl<T>) :
    RecyclerView.Adapter<RvViewHolder>() {

    private var data: ArrayList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val datum = data[position]
        adapterDsl.viewBindings.forEach { viewBinding ->
            viewBinding.mapper(datum, holder.itemView.findViewById(viewBinding.viewId))
        }
    }

    fun addItem(item: T) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }
}