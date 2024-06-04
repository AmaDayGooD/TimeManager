package com.example.timemanager.ui.screens.users_top.recycle_view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ItemUsersTopBinding
import com.example.timemanager.entity.Profile
import com.omega_r.libs.omegatypes.Color
import com.omega_r.libs.omegatypes.textColor

/**
 * Created by Alexander Shibaev on 26.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */

class UsersTopHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemUsersTopBinding.bind(itemView)

    @SuppressLint("ResourceType")
    fun onBind(item: Profile, position: Int) = with(binding) {
        textViewUserName.text = item.username
        textViewBalance.text = item.numberCompletedTask.toString()
        setColorItem(position)
    }

    @SuppressLint("ResourceAsColor")
    private fun setColorItem(position: Int) = with(binding) {
        when (position) {
            0 -> {
                layoutRoot.backgroundTintList =
                    ColorStateList.valueOf(getColor(itemView.context, R.color.first_position))
                textViewUserName.setTextColor(getColor(itemView.context, R.color.light_main))
                textViewBalance.setTextColor(getColor(itemView.context, R.color.light_main))
            }

            1 -> {
                layoutRoot.backgroundTintList =
                    ColorStateList.valueOf(getColor(itemView.context, R.color.two_position))
                textViewUserName.setTextColor(getColor(itemView.context, R.color.light_main))
                textViewBalance.setTextColor(getColor(itemView.context, R.color.light_main))
            }

            2 -> {
                layoutRoot.backgroundTintList =
                    ColorStateList.valueOf(getColor(itemView.context, R.color.three_position))
                textViewUserName.setTextColor(getColor(itemView.context, R.color.light_main))
                textViewBalance.setTextColor(getColor(itemView.context, R.color.light_main))
            }
        }
    }
}