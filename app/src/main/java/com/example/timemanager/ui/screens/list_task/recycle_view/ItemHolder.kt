package com.example.timemanager.ui.screens.list_task.recycle_view

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.data.Condition
import com.example.timemanager.data.Importance
import com.example.timemanager.databinding.ItemTaskBinding
import com.example.timemanager.entity.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ItemHolder(private val itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemTaskBinding.bind(itemView)

    @RequiresApi(Build.VERSION_CODES.O)
    fun onBindView(itemTask: Task) = with(binding) {
        listener.setState(cardTaskState, itemTask.condition ?: Condition.Open)
        textViewTaskName.text = itemTask.taskName
        textTaskTimeLimit.text = formatDate(itemTask.taskStart)
        textTaskDateLimit.text = listener.getResourcesString(R.string.time_range, formatTime(itemTask.taskStart), formatTime(itemTask.taskEnd))
        textAward.text = itemTask.award
        setSeriousness(itemTask.seriousness ?: Importance.Low)
        taskItem.setOnClickListener {
            listener.onClickOpenTask(itemTask.idTask!!)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDate(inputDate: LocalDateTime): String {
        return inputDate.format(DateTimeFormatter.ofPattern("dd MMMM").withLocale(Locale("ru")))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatTime(inputDate:LocalDateTime): String {
        return inputDate.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    private fun setSeriousness(seriousness: Importance) {
        when (seriousness) {
            Importance.Low -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_lightning_small)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_lightning_small)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning_small)
            }

            Importance.Medium -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_lightning_small)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning_small)
            }

            Importance.High -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_lightning_small)
            }

            Importance.ExtraHigh -> {
                binding.icLowSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icMediumSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icHighSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
                binding.icExtraHighSeriousness.setImageResource(R.drawable.ic_fill_lightning_small)
            }
        }
    }

}