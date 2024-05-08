package com.example.timemanager.ui.screens.my_task

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.timemanager.R
import com.example.timemanager.data.Condition
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.Importance
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.databinding.ActivityMyTaskBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseActivity
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
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
    private lateinit var dialogAcceptTask: Dialog

    private lateinit var buttonBack: ImageButton
    private lateinit var textAward: TextView
    private lateinit var textViewNameTask: TextView
    private lateinit var textViewDescriptionTask: TextView
    private lateinit var buttonEditTask: MaterialButton
    private lateinit var buttonTaskCompleted: Button
    private lateinit var buttonTaskNotCompleted: Button
    private lateinit var textTaskPerformer: TextView
    private lateinit var buttonTaskState: Button
    private lateinit var buttonData: Button
    private lateinit var buttonTime: Button
    private lateinit var labelExecutor: LinearLayout

    private lateinit var seriousness: Importance
    private var currentTask: Task? = null

    private var idTask: Int = -1
    private var isParent: Boolean = false
    private var modeEditTask: Boolean = false

    private var taskStart: LocalDateTime = LocalDateTime.now()
    private var taskEnd: LocalDateTime = LocalDateTime.now().plusMinutes(10)
    private var isError = false

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
        buttonData = binding.buttonSetData
        buttonTime = binding.buttonSetTime
        labelExecutor = binding.labelExecutor

        buttonBack.setOnClickListener {
            finish()
        }

    }

    override fun setTaskInfo(task: Task?, userRole: Role, taskPerformer: Profile?) {
        currentTask = task
        taskStart = task?.taskStart ?: LocalDateTime.now()
        taskEnd = task?.taskEnd ?: LocalDateTime.now()

        currentTask?.let { currentTask ->
            seriousness = currentTask.seriousness ?: Importance.Medium
            textAward.text = currentTask.award
            textViewNameTask.text = currentTask.taskName
            buttonData.text = formatTaskDate(currentTask.taskStart.toLocalDate())
            buttonTime.text =
                getString(R.string.deadlines, currentTask.taskStart.toLocalTime(), currentTask.taskEnd.toLocalTime())
            setSeriousness(currentTask.seriousness ?: Importance.Medium)
            setState(buttonTaskState, currentTask.condition ?: Condition.Open)
        }

        if (currentTask?.description.isNullOrEmpty()) textViewDescriptionTask.visibility = View.GONE
        else textViewDescriptionTask.text = currentTask?.description

        when (userRole) {
            Role.Child -> {
                labelExecutor.visibility = View.GONE
                textTaskPerformer.visibility = View.GONE
                buttonTaskNotCompleted.visibility = View.GONE
                buttonTaskState.isEnabled = false
                isParent = false
            }

            Role.Parent -> {
                labelExecutor.visibility = View.VISIBLE
                textTaskPerformer.apply {
                    visibility = View.VISIBLE
                    text = taskPerformer?.username
                }
                buttonTaskNotCompleted.visibility = View.VISIBLE

                isParent = true
            }
        }
        setRole()

        if (currentTask?.condition == Condition.Accept) {
            goneAllButtons()
        }
        closeLoading()
    }

    private fun formatTaskDate(taskDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru")) // Форматирование даты в виде "день Месяц"
        return taskDate.format(formatter)
    }

    private fun goneAllButtons() {
        buttonEditTask.visibility = View.GONE
        buttonTaskCompleted.visibility = View.GONE
        buttonTaskNotCompleted.visibility = View.GONE
        buttonTaskState.isEnabled = false
    }

    @SuppressLint("ResourceAsColor")
    private fun setRole() {
        if (isParent) {
            buttonEditTask.visibility = View.VISIBLE
            buttonTaskNotCompleted.visibility = View.VISIBLE


            buttonEditTask.setOnClickListener {
                changeEnableButton()
                if (modeEditTask) {
                    buttonEditTask.text = getString(R.string.edit_task)
                    applyTaskChanges()
                    modeEditTask = false
                } else {
                    buttonEditTask.text = getString(R.string.accept_edit)
                    modeEditTask = true
                }
                enableEditField()
            }

            if (currentTask?.condition != Condition.Reject) {

                buttonTaskNotCompleted.apply {
                    isEnabled = true
                    backgroundTintList = ColorStateList.valueOf(getColor(R.color.error))
                }

                buttonTaskNotCompleted.setOnClickListener {
                    val newTask = (currentTask as DataTask).copy(
                        status = Condition.Reject.name
                    )
                    presenter.applyChanges(newTask)
                }

            } else buttonTaskNotCompleted.apply {
                isEnabled = false
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.button_disable))
            }

            if (currentTask?.condition != Condition.Accept) {
                buttonTaskCompleted.apply {
                    isEnabled = true
                    backgroundTintList = ColorStateList.valueOf(getColor(R.color.completed))
                }

                buttonTaskCompleted.setOnClickListener {
                    showDialogAcceptTask()
                }

            } else buttonTaskCompleted.apply {
                isEnabled = false
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.button_disable))
            }

        } else {
            buttonEditTask.visibility = View.GONE
            buttonTaskNotCompleted.visibility = View.GONE

            if (currentTask?.condition == Condition.Completed || currentTask?.condition == Condition.Accept) {
                buttonTaskCompleted.visibility = View.GONE
            } else {
                buttonTaskCompleted.setOnClickListener {
                    presenter.taskCompleted()
                }
            }
        }
    }

    private fun changeEnableButton() {
        if (modeEditTask) {
            buttonData.isClickable = false
            buttonTime.isClickable = false
            buttonTaskState.visibility = View.VISIBLE
            buttonTaskCompleted.visibility = View.VISIBLE
            buttonTaskNotCompleted.visibility = View.VISIBLE
        } else {
            buttonData.isClickable = true
            buttonTime.isClickable = true
            buttonTaskState.visibility = View.GONE
            buttonTaskCompleted.visibility = View.GONE
            buttonTaskNotCompleted.visibility = View.GONE

            buttonData.setOnClickListener {
                showDatePickerDialog(true)
            }
            buttonTime.setOnClickListener {
                showTimePickerDialog(true, taskStart.toLocalDate())
            }
        }
    }

    private fun enableEditField() {
        val seriousnessMap = mapOf(
            binding.icLowSeriousness to Importance.Low,
            binding.icMediumSeriousness to Importance.Medium,
            binding.icHighSeriousness to Importance.High,
            binding.icExtraHighSeriousness to Importance.ExtraHigh
        )

        setViewProperties(textViewNameTask, modeEditTask, if (modeEditTask) R.color.black else R.color.white)
        setViewProperties(textAward, modeEditTask, if (modeEditTask) R.color.black else R.color.white)
        setViewProperties(textViewDescriptionTask, modeEditTask, if (modeEditTask) R.color.black else R.color.white)

        seriousnessMap.forEach { (view, importance) ->
            view.apply {
                isClickable = modeEditTask
                setOnClickListener {
                    setSeriousness(importance)
                }
            }
        }
    }

    private fun applyTaskChanges() {
        val newTask = (currentTask as DataTask).copy(
            taskName = textViewNameTask.text.toString(),
            description = textViewDescriptionTask.text.toString(),
            importance = seriousness.name,
            award = textAward.text.toString(),
            startDateTime = taskStart.toString(),
            endDateTime = taskEnd.toString()
        )
        presenter.applyChanges(newTask)
    }

    private fun setViewProperties(view: View, isEnabled: Boolean, colorResId: Int) {
        view.apply {
            this.isEnabled = isEnabled
            backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this@MyTaskActivity, colorResId))
        }
    }

    private fun setSeriousness(importance: Importance) {
        when (importance) {
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
        seriousness = importance
    }

    override fun taskCompletedShowDialog() {
        showInfoDialog(context = this, title = getString(R.string.task_completed), text = getString(R.string.text_compete_task))
        finish()
    }

    override fun showLoading() {
        showDialog(this)
    }

    // Метод для отображения диалога выбора даты
    private fun showDatePickerDialog(isStart: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val date = LocalDate.of(year, month + 1, dayOfMonth)
            taskStart = taskStart.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
            taskEnd = taskEnd.withYear(year).withMonth(month + 1).withDayOfMonth(dayOfMonth)
            buttonData.text = formatTaskDate(date)
        }, year, month, dayOfMonth)

        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    // Метод для отображения диалога выбора времени
    private fun showTimePickerDialog(isStart: Boolean, selectedDate: LocalDate) {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            val selectedTime = LocalTime.of(hourOfDay, minute)
            if (isStart) {
                taskStart = taskStart.withHour(hourOfDay).withMinute(minute)
                buttonTime.text = LocalTime.of(selectedTime.hour, selectedTime.minute).toString()
                showTimePickerDialog(false, selectedDate)
            } else {
                taskEnd = taskEnd.withHour(hourOfDay).withMinute(minute)
                buttonTime.text =
                    (buttonTime.text.toString()) + "-" + (LocalTime.of(selectedTime.hour, selectedTime.minute).toString())
                isError = false
                checkDates()
            }

            println("MyLog selectedTime $taskStart $taskEnd")
        }, hourOfDay, minute, true)


        timePickerDialog.show()
    }

    private fun checkDates() {
        when {
            taskStart > taskEnd -> {
                isError = true
                showInfoDialog(
                    this,
                    getString(R.string.title_incorrect_start_end_data),
                    getString(R.string.info_incorrect_start_end_data),
                    true
                )
                buttonTime.setBackgroundColor(getColor(R.color.error))
                buttonEditTask.setBackgroundColor(getColor(R.color.error))
                buttonEditTask.isClickable = false
            }

            taskStart < LocalDateTime.now() || taskEnd < LocalDateTime.now() -> {
                isError = true
                closeDialog()
                showInfoDialog(
                    this, getString(R.string.title_incorrect_start_end_data), getString(R.string.info_incorrect_post_time), true
                )
                buttonTime.setBackgroundColor(getColor(R.color.error))
                buttonEditTask.setBackgroundColor(getColor(R.color.error))
                buttonEditTask.isClickable = false
            }

            else -> {
                buttonTime.setBackgroundColor(getColor(R.color.main))
                buttonEditTask.setBackgroundColor(getColor(R.color.main))
                buttonEditTask.isClickable = true
            }
        }
    }

    private fun showDialogAcceptTask() {
        val task = presenter.getTask()
        dialogAcceptTask = Dialog(this, R.style.DialogStyle)
        dialogAcceptTask.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogAcceptTask.setCancelable(false)
        dialogAcceptTask.setContentView(R.layout.dialog_accept_task)

        val buttonPositive = dialogAcceptTask.findViewById<Button>(R.id.button_positive)
        val buttonNegative = dialogAcceptTask.findViewById<Button>(R.id.button_negative)

        buttonPositive.setOnClickListener {
            val newTask = (currentTask as DataTask).copy(
                status = Condition.Accept.name
            )
            presenter.applyChanges(newTask)
            presenter.payReward(task.award.toFloat())
            finish()
        }

        buttonNegative.setOnClickListener {
            dialogAcceptTask.dismiss()
        }

        dialogAcceptTask.show()

    }

    private fun setState(button: Button, state: Condition) {
        button.apply {
            text = getString(state.textResId)
            setBackgroundColor(ContextCompat.getColor(this@MyTaskActivity, state.colorRes))
        }
    }

    override fun closeDialogChangeStatus() {
        dialogChangeStatusTask.dismiss()
    }

    override fun closeLoading() {
        closeDialog()
    }
}