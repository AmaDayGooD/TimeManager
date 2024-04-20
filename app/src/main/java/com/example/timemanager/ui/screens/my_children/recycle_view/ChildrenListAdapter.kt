package com.example.timemanager.ui.screens.my_children.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task

class ChildrenListAdapter: RecyclerView.Adapter<ItemHolder>() {

    private var listItem: List<Profile> = emptyList()

    fun setList(list: List<Profile>) {
        listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBindView(listItem[position])
    }
}