package com.example.timemanager.ui.screens.awards.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.databinding.ItemAwardBinding
import com.example.timemanager.entity.Award
import com.example.timemanager.entity.Profile


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class AwardHolder(
    private val itemView: View,
    private val profile: Profile,
    private val listener: OnItemClickListener,
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemAwardBinding.bind(itemView)

    fun onBindView(itemAward: Award) = with(binding) {
        if (profile.count != 0) {
            progressBar.visibility = View.VISIBLE
            progressBar.max = itemAward.priceAward
            progressBar.progress = profile.count ?: 0
        }
        textViewNameAward.text = itemAward.nameAward
        textViewDescriptionAward.text = itemAward.description
        textViewAward.text = itemAward.priceAward.toString()

        if (profile.userRole == Role.Child)
            linearLayoutItemAward.setOnClickListener {
                listener.onClickGetAward(itemAward.idAward!!)
            }
    }


}