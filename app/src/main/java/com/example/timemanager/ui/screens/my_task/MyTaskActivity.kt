package com.example.timemanager.ui.screens.my_task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.timemanager.R
import com.example.timemanager.data.Importance
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.databinding.ActivityMyTaskBinding
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseActivity

class MyTaskActivity : BaseActivity(R.layout.activity_my_task), MyTaskView {

    private lateinit var binding: ActivityMyTaskBinding

    override val presenter: MyTaskPresenter by providePresenter {
        MyTaskPresenter(intent.getIntExtra(TASK_ID, -1))
    }

    companion object {
        const val TASK_ID = "taskId"

        fun createIntentMyTask(context: Context, idTask: Int): Intent {
            return Intent(context, MyTaskActivity::class.java).putExtra(TASK_ID, idTask)
        }
    }

    private lateinit var buttonBack: ImageButton
    private lateinit var textViewNameTask: TextView
    private lateinit var textViewDescriptionTask: TextView
    private lateinit var buttonTaskCompleted: Button
    private lateinit var buttonTaskNotCompleted: Button

    private var idTask: Int = -1

    private var isParent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idTask = intent.getIntExtra(TASK_ID, -1)

        buttonBack = binding.buttonBack
        textViewNameTask = binding.textViewNameTask
        textViewDescriptionTask = binding.textViewDescriptionTask
        buttonTaskCompleted = binding.buttonTaskCompleted
        buttonTaskNotCompleted = binding.buttonTaskNotCompleted

        buttonBack.setOnClickListener {
            finish()
        }

        buttonTaskCompleted.setOnClickListener {
            if (isParent) {

            } else {

            }
        }

        buttonTaskNotCompleted.setOnClickListener {
            log("isParent buttonTaskCompleted")
            if (isParent) {

            } else {

            }

        }
    }

    override fun setTaskInfo(task: Task?, userRole: Role) {
        textViewNameTask.text = task?.taskName
        setSeriousness(task?.seriousness ?: Importance.Medium)
        if (task?.description.isNullOrEmpty()) textViewDescriptionTask.visibility = View.GONE
        else textViewDescriptionTask.text = task?.description

        when (userRole) {
            Role.Child -> {
                buttonTaskNotCompleted.visibility = View.GONE
                isParent = false
            }

            Role.Parent -> {
                buttonTaskNotCompleted.visibility = View.VISIBLE
                isParent = true
            }
        }
        log("$userRole $isParent")
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

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}