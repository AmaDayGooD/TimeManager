package com.example.timemanager.ui.screens.list_task.recycle_view

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.data.Importance
import com.example.timemanager.databinding.ItemTaskBinding
import com.example.timemanager.entity.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ItemHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTaskBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.O)
    fun onBindView(itemTask: Task) = with(binding) {
        println("MyLog idTask ${itemTask.idTask}")
        textViewTaskName.text = itemTask.taskName
        textTaskLimit.text = formatDate(itemTask.limit)
        textAward.text = itemTask.award
        setSeriousness(itemTask.seriousness ?: Importance.Low)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDate(inputDate: LocalDateTime): String {
        val outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy\nHH:mm")
        return inputDate.format(outputFormat)
    }

    private fun setSeriousness(seriousness: Importance) {
        when (seriousness) {
            Importance.Low -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_lightning)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_lightning)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning)
            }
            Importance.Medium -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_lightning)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning)
            }
            Importance.High -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning)
            }
            Importance.ExtraHigh -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_fill_lightning)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_fill_lightning)
            }
        }
    }

}