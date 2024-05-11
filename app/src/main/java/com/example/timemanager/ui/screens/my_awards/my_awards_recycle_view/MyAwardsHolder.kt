package com.example.timemanager.ui.screens.my_awards.my_awards_recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.databinding.ItemMyAwardBinding
import com.example.timemanager.entity.Award
import java.text.SimpleDateFormat
import java.util.Locale


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class MyAwardsHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemMyAwardBinding.bind(itemView)

    fun onBind(item: Award) = with(binding) {
        textViewNameAward.text = item.nameAward
        textViewDateAwarded.text = formatDate(item.awarded?.awarded.orEmpty())
        textViewDescriptionAward.text = item.description
        textViewAward.text = item.priceAward.toString()
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
}