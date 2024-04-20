package com.example.timemanager.ui.screens.my_children.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.databinding.ItemChildBinding
import com.example.timemanager.entity.Profile
import com.omega_r.libs.omegaintentbuilder.utils.ExtensionUtils.Companion.isNullOrLessZero

class ItemHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemChildBinding.bind(itemView)

    fun onBindView(itemProfile: Profile) = with(binding) {
        textViewName.text = itemProfile.username
        if (itemProfile.count.isNullOrLessZero()) textAward.text = "0"
        else textAward.text = itemProfile.count.toString()
    }
}