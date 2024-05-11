package com.example.timemanager.ui.screens.my_awards.my_awards_recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.databinding.ItemMyAwardBinding
import com.example.timemanager.entity.Award


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class MyAwardsHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemMyAwardBinding.bind(itemView)

    fun onBind(item: Award) = with(binding) {
        textViewNameAward.text = item.nameAward
        textViewDateAwarded.text = item.awarded?.awarded.orEmpty()
        textViewDescriptionAward.text = item.description
        textViewAward.text = item.priceAward.toString()
    }
}