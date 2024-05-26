package com.example.timemanager.ui.screens.users_top.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.entity.Profile


/**
 * Created by Alexander Shibaev on 26.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class UsersTopListAdapter : RecyclerView.Adapter<UsersTopHolder>() {

    private var listUsers: List<Profile> = emptyList()

    fun setList(list: List<Profile>) {
        listUsers = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersTopHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users_top, parent, false)
        return UsersTopHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: UsersTopHolder, position: Int) {
        holder.onBind(listUsers[position], position)
    }
}