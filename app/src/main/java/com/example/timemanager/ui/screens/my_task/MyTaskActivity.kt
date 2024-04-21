package com.example.timemanager.ui.screens.my_task

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.timemanager.R
import com.example.timemanager.data.Condition
import com.example.timemanager.data.Importance
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.databinding.ActivityMyTaskBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseActivity
import com.google.android.material.button.MaterialButton
import com.omega_r.bind.model.binders.bindBackgroundTintColor

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

    private lateinit var dialogChangeStatusTask: Dialog

    private lateinit var buttonBack: ImageButton
    private lateinit var textAward: TextView
    private lateinit var textViewNameTask: TextView
    private lateinit var textViewDescriptionTask: TextView
    private lateinit var buttonEditTask: MaterialButton
    private lateinit var buttonTaskCompleted: Button
    private lateinit var buttonTaskNotCompleted: Button
    private lateinit var textTaskPerformer: TextView
    private lateinit var buttonTaskState: Button

    private lateinit var seriousness: Importance

    private var idTask: Int = -1
    private var isParent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idTask = intent.getIntExtra(TASK_ID, -1)

        buttonBack = binding.buttonBack
        textAward = binding.textAward
        textViewNameTask = binding.textViewNameTask
        textViewDescriptionTask = binding.textViewDescriptionTask
        buttonEditTask = binding.buttonEditTask
        buttonTaskCompleted = binding.buttonTaskCompleted
        buttonTaskNotCompleted = binding.buttonTaskNotCompleted
        textTaskPerformer = binding.textTaskPerformer
        buttonTaskState = binding.buttonTaskState

        buttonBack.setOnClickListener {
            finish()
        }

        buttonTaskState.setOnClickListener {
            showInputDialog()
        }


        buttonTaskCompleted.setOnClickListener {
            if (isParent) {
                // TODO
            } else {
                // TODO
            }
        }

        buttonTaskNotCompleted.setOnClickListener {
            log("isParent buttonTaskCompleted")
            if (isParent) {
                // TODO
            } else {
                // TODO
            }

        }
    }

    override fun setTaskInfo(task: Task?, userRole: Role, taskPerformer: Profile?) {
        seriousness = task?.seriousness ?: Importance.Medium
        textAward.text = task?.award
        textViewNameTask.text = task?.taskName
        setSeriousness(task?.seriousness ?: Importance.Medium)
        setState(buttonTaskState, task?.condition ?: Condition.Open)
        if (task?.description.isNullOrEmpty()) textViewDescriptionTask.visibility = View.GONE
        else textViewDescriptionTask.text = task?.description

        when (userRole) {
            Role.Child -> {
                textTaskPerformer.visibility = View.GONE
                buttonTaskNotCompleted.visibility = View.GONE
                buttonTaskState.isEnabled = false
                isParent = false
            }

            Role.Parent -> {
                textTaskPerformer.apply {
                    visibility = View.VISIBLE
                    text = taskPerformer?.username
                }
                buttonTaskNotCompleted.visibility = View.VISIBLE

                isParent = true
            }
        }
        setRole()
        closeLoading()
    }

    private fun setRole() {
        if (isParent) {
            buttonEditTask.visibility = View.VISIBLE
            buttonTaskNotCompleted.visibility = View.VISIBLE

            buttonEditTask.setOnClickListener {
                enableEditField()
            }

            buttonTaskNotCompleted.setOnClickListener {

            }
            buttonTaskCompleted.setOnClickListener {

            }

        } else {
            buttonEditTask.visibility = View.GONE
            buttonTaskNotCompleted.visibility = View.GONE

            buttonTaskNotCompleted.setOnClickListener {

            }
            buttonTaskCompleted.setOnClickListener {

            }
        }
    }

    private fun enableEditField() {
        setViewProperties(textViewNameTask, true, R.color.black)
        setViewProperties(textAward, true, R.color.black)
        setViewProperties(textViewDescriptionTask, true, R.color.black)


        with(binding) {
            icLowSeriousness.apply {
                isClickable = true
                setOnClickListener {
                    setSeriousness(Importance.Low)
                }
            }
            icMediumSeriousness.apply {
                isClickable = true
                setOnClickListener {
                    setSeriousness(Importance.Medium)
                }
            }
            icHighSeriousness.apply {
                isClickable = true
                setOnClickListener {
                    setSeriousness(Importance.High)
                }
            }
            icExtraHighSeriousness.apply {
                isClickable = true
                setOnClickListener {
                    setSeriousness(Importance.ExtraHigh)
                }
            }
        }
    }

    private fun setViewProperties(view: View, isEnabled: Boolean, colorResId: Int) {
        view.apply {
            this.isEnabled = isEnabled
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@MyTaskActivity, colorResId))
        }
    }

    private fun setState(button: Button, state: Condition) {
        button.apply {
            text = getString(state.textResId)
            setBackgroundColor(ContextCompat.getColor(this@MyTaskActivity, state.colorRes))
        }
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

    private fun showInputDialog() {
        val task = presenter.getTask()
        dialogChangeStatusTask = Dialog(this, R.style.DialogStyle)
        dialogChangeStatusTask.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogChangeStatusTask.setCancelable(true)
        dialogChangeStatusTask.setContentView(R.layout.dialog_change_state_task)

        val taskState = dialogChangeStatusTask.findViewById<Button>(R.id.button_task_state)
        val buttonStateOpen = dialogChangeStatusTask.findViewById<Button>(R.id.button_select_state_open)
        val buttonStateCompleted = dialogChangeStatusTask.findViewById<Button>(R.id.button_select_state_completed)
        val buttonStateReject = dialogChangeStatusTask.findViewById<Button>(R.id.button_select_state_reject)
        val buttonStateAccept = dialogChangeStatusTask.findViewById<Button>(R.id.button_select_state_accept)
        val buttonApplyChange = dialogChangeStatusTask.findViewById<Button>(R.id.button_apply_changes)

        var taskStatus: Condition = task.condition ?: Condition.Open

        taskState?.apply {
            text = getString(taskStatus.textResId)
            setBackgroundColor(ContextCompat.getColor(this@MyTaskActivity, taskStatus.colorRes))
        }

        val buttons = arrayOf(
            buttonStateOpen to Condition.Open,
            buttonStateCompleted to Condition.Completed,
            buttonStateReject to Condition.Reject,
            buttonStateAccept to Condition.Accept
        )

        buttons.forEach { (button, condition) ->
            button?.setOnClickListener {
                taskState?.apply {
                    text = getString(condition.textResId)
                    setBackgroundColor(ContextCompat.getColor(this@MyTaskActivity, condition.colorRes))
                }
                taskStatus = condition
            }
        }

        buttonApplyChange.setOnClickListener {
            presenter.updateTaskStatus(taskStatus)
        }

        val window = dialogChangeStatusTask.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialogChangeStatusTask.show()
    }

    override fun closeDialogChangeStatus() {
        dialogChangeStatusTask.dismiss()
    }

    override fun closeLoading() {
        closeDialog()
    }
}