package com.example.timemanager.ui.screens.my_task

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.timemanager.R
import com.example.timemanager.data.Condition
import com.example.timemanager.data.Importance
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.databinding.ActivityMyTaskBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseActivity
import com.omega_r.libs.omegatypes.backgroundColor

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

    private lateinit var dialog: Dialog

    private lateinit var buttonBack: ImageButton
    private lateinit var textViewNameTask: TextView
    private lateinit var textViewDescriptionTask: TextView
    private lateinit var buttonTaskCompleted: Button
    private lateinit var buttonTaskNotCompleted: Button
    private lateinit var textTaskPerformer: TextView
    private lateinit var buttonTaskState: Button

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
        textViewNameTask.text = task?.taskName
        setSeriousness(task?.seriousness ?: Importance.Medium)
        setState(task?.condition ?: Condition.Open)
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
    }

    private fun setState(state: Condition) {
        when (state) {
            Condition.Accept -> {
                buttonTaskState.apply {
                    setBackgroundColor(getColor(R.color.accept))
                    text = getString(R.string.task_accept)
                }
            }

            Condition.Completed -> {
                buttonTaskState.apply {
                    setBackgroundColor(getColor(R.color.completed))
                    text = getString(R.string.task_completed)
                }
            }

            Condition.Open -> {
                buttonTaskState.apply {
                    setBackgroundColor(getColor(R.color.main))
                    text = getString(R.string.task_open)
                }

            }

            Condition.Reject -> {
                buttonTaskState.apply {
                    setBackgroundColor(getColor(R.color.reject))
                    text = getString(R.string.task_reject)
                }
            }
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
        dialog = Dialog(this, R.style.DialogStyle)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_change_state_task)

        val taskState = dialog.findViewById<Button>(R.id.button_task_state)
        val buttonStateOpen = dialog.findViewById<Button>(R.id.button_select_state_open)
        val buttonStateCompleted = dialog.findViewById<Button>(R.id.button_select_state_completed)
        val buttonStateReject = dialog.findViewById<Button>(R.id.button_select_state_reject)
        val buttonStateAccept = dialog.findViewById<Button>(R.id.button_select_state_accept)
        val buttonApplyChange = dialog.findViewById<Button>(R.id.button_apply_changes)

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

        // Для каждой кнопки устанавливаем обработчик нажатия
        buttons.forEach { (button, condition) ->
            button?.setOnClickListener {
                taskState?.apply {
                    text = getString(condition.textResId)
                    setBackgroundColor(ContextCompat.getColor(this@MyTaskActivity, condition.colorRes))
                }
                taskStatus = condition
            }
        }


//        buttonStateOpen.setOnClickListener {
//            taskState?.apply {
//                text = getString(R.string.task_open)
//                setBackgroundColor(getColor(R.color.main))
//            }
//            taskStatus = Condition.Open
//        }
//
//        buttonStateCompleted.setOnClickListener {
//            taskState?.apply {
//                text = getString(R.string.task_completed)
//                setBackgroundColor(getColor(R.color.completed))
//            }
//            taskStatus = Condition.Completed
//        }
//
//        buttonStateReject.setOnClickListener {
//            taskState?.apply {
//                text = getString(R.string.task_reject)
//                setBackgroundColor(getColor(R.color.reject))
//            }
//            taskStatus = Condition.Reject
//        }
//
//        buttonStateAccept.setOnClickListener {
//            taskState?.apply {
//                text = getString(R.string.task_accept)
//                setBackgroundColor(getColor(R.color.accept))
//            }
//            taskStatus = Condition.Accept
//        }

        buttonApplyChange.setOnClickListener {
            presenter.updateTaskStatus(taskStatus)
        }

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }


    override fun closeDialogChangeStatus() {
        dialog.dismiss()
    }

    override fun closeLoading() {
        closeDialog()
    }
}