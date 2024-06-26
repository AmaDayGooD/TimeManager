package com.example.timemanager.ui.screens.awards.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.entity.Award
import com.example.timemanager.entity.Profile


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class AwardListAdapter(private val listener: OnItemClickListener, private val profile: Profile) :
    RecyclerView.Adapter<AwardHolder>() {

    private var listAward: List<Award> = emptyList()

    fun setList(list: List<Award>) {
        listAward = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_award, parent, false)
        return AwardHolder(view, profile, listener)
    }

    override fun getItemCount(): Int {
        return listAward.size
    }

    override fun onBindViewHolder(holder: AwardHolder, position: Int) {
        holder.onBindView(listAward[position])
    }
}