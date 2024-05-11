package com.example.timemanager.ui.screens.my_awards.my_awards_recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.entity.Award


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class MyAwardsListAdapter() : RecyclerView.Adapter<MyAwardsHolder>() {

    private var listAwards: List<Award> = emptyList()

    fun setList(list: List<Award>) {
        listAwards = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAwardsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_award, parent, false)
        return MyAwardsHolder(view)
    }

    override fun getItemCount(): Int {
        return listAwards.size
    }

    override fun onBindViewHolder(holder: MyAwardsHolder, position: Int) {
        holder.onBind(listAwards[position])
    }
}