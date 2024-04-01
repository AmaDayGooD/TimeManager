package com.example.timemanager.ui.screens.list_task.recycle_view

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.entity.Task

class TaskListAdapter : RecyclerView.Adapter<ItemHolder>() {

    private var listItem: List<Task> = emptyList()

    fun setList(list: List<Task>) {
        listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ItemHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.onBindView(listItem[position])
    }

    override fun getItemCount(): Int {
        val size = listItem.size
        return size
    }
}