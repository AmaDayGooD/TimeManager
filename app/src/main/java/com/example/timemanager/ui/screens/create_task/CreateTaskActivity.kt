package com.example.timemanager.ui.screens.create_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.timemanager.R
import com.example.timemanager.data.Importance
import com.example.timemanager.databinding.ActivityCreateTaskBinding
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.list_task.TasksActivity.Companion.createIntentTaskActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class CreateTaskActivity : BaseActivity(R.layout.activity_create_task), CreateTaskView {

    private lateinit var binding: ActivityCreateTaskBinding

    override val presenter: CreateTaskPresenter by providePresenter {
        CreateTaskPresenter()
    }

    companion object {
        fun createIntentCreateTaskActivity(context: Context): Intent {
            return Intent(context, CreateTaskActivity::class.java)
        }
    }

    private lateinit var buttonBack: ImageButton
    private lateinit var spinnerChild: Spinner
    private lateinit var titleTask: EditText
    private lateinit var descriptionTask: EditText
    private lateinit var textAward: EditText
    private lateinit var buttonTaskDate: Button
    private lateinit var buttonTaskTime: Button
    private lateinit var buttonAcceptCreateTask: Button

    var list = emptyList<String>()
    private var taskName: String = ""
    private var taskDescription: String = ""
    private var seriousness: Importance = Importance.Low
    private var award: String = ""
    private var taskDate: LocalDate = LocalDate.now()
    private var dateTimeStart: LocalTime = LocalTime.now()
    private var dateTimeEnd: LocalTime = LocalTime.now().plusMinutes(5)

    private var isError = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinnerChild = binding.spinnerChilds
        textAward = binding.textAward
        buttonBack = binding.buttonBack
        titleTask = binding.textViewNameTask
        descriptionTask = binding.textViewDescriptionTask

        buttonTaskDate = binding.buttonSetData
        buttonTaskTime = binding.buttonSetTime
        buttonAcceptCreateTask = binding.buttonAcceptNewTask
        setStateButton()
        buttonBack.setOnClickListener {
            finish()
        }

        buttonTaskDate.setOnClickListener {
            showDatePickerDialog(true)
        }

        buttonTaskTime.setOnClickListener {
            showTimePickerDialog(true, taskDate)
        }

        titleTask.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                taskName = p0.toString()
                setStateButton()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        descriptionTask.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                taskDescription = p0.toString()
                setStateButton()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        textAward.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                award = p0.toString()
                setStateButton()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })


        enableEditField()

        buttonAcceptCreateTask.setOnClickListener {
            presenter.createNewTask(
                taskName,
                taskDescription,
                seriousness,
                award,
                taskDate.atTime(dateTimeStart),
                taskDate.atTime(dateTimeEnd)
            )
        }
    }

    override fun setNameChild(nameChild: List<String>) {
        list = nameChild
        initSpinner()
    }

    private fun initSpinner() {
        val spinnerAdapter = ArrayAdapter(this@CreateTaskActivity, R.layout.spinner_row, R.id.text_view_text, list)
        spinnerChild.adapter = spinnerAdapter
        spinnerChild.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.setChildId(list[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun showResultCreateTask(result: Boolean) {
        if (result) {
            Toast.makeText(this, getString(R.string.successfully_created), Toast.LENGTH_SHORT ).show()
            startActivity(createIntentTaskActivity(this))
        } else {
            showInfoDialog(getString(R.string.error), getString(R.string.failed_created))
        }
        closeLoading()
    }

    private fun enableEditField() {
        val seriousnessMap = mapOf(
            binding.icLowSeriousness to Importance.Low,
            binding.icMediumSeriousness to Importance.Medium,
            binding.icHighSeriousness to Importance.High,
            binding.icExtraHighSeriousness to Importance.ExtraHigh
        )

        seriousnessMap.forEach { (view, importance) ->
            view.apply {
                setOnClickListener {
                    setSeriousness(importance)
                }
            }
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

    // Метод для отображения диалога выбора даты
    private fun showDatePickerDialog(isStart: Boolean) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            taskDate = LocalDate.of(year, month + 1, dayOfMonth)
            checkDate()
            buttonTaskDate.text = formatTaskDate(taskDate)
        }, year, month, dayOfMonth)

        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun formatTaskDate(taskDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru")) // Форматирование даты в виде "день Месяц"
        return taskDate.format(formatter)
    }

    // Метод для отображения диалога выбора времени
    private fun showTimePickerDialog(isStart: Boolean, selectedDate: LocalDate) {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            val selectedTime = LocalTime.of(hourOfDay, minute)
            if (isStart) {
                dateTimeStart = LocalTime.of(selectedTime.hour, selectedTime.minute)
                buttonTaskTime.text = dateTimeStart.toString()
                showTimePickerDialog(false, taskDate)
            } else {
                dateTimeEnd = LocalTime.of(selectedTime.hour, selectedTime.minute)
                buttonTaskTime.text = (buttonTaskTime.text.toString()) + "-" + (dateTimeEnd.toString())

                if (dateTimeStart > dateTimeEnd) {
                    isError = true
                    showInfoDialog(
                        getString(R.string.title_incorrect_start_end_data),
                        getString(R.string.info_incorrect_start_end_data),
                        true
                    )
                    buttonTaskTime.setBackgroundColor(getColor(R.color.error))

                } else {
                    isError = false
                    buttonTaskTime.setBackgroundColor(getColor(R.color.main))
                    buttonTaskDate.setBackgroundColor(getColor(R.color.main))
                }
                checkDate()

            }
            setStateButton()
        }, hourOfDay, minute, true)


        timePickerDialog.show()
    }

    private fun checkDate() {
        if (taskDate.atTime(dateTimeStart) < LocalDateTime.now() && taskDate.atTime(dateTimeEnd) < LocalDateTime.now()) {
            closeDialog()
            isError = true
            showInfoDialog(
                getString(R.string.title_incorrect_start_end_data),
                getString(R.string.info_incorrect_post_time),
                true
            )
            buttonTaskDate.setBackgroundColor(getColor(R.color.error))
            buttonTaskTime.setBackgroundColor(getColor(R.color.error))
        }
    }

    private fun setStateButton() {
        if (taskName.isEmpty() || taskDescription.isEmpty() || award.isEmpty() || dateTimeStart == null || dateTimeEnd == null || isError) {
            buttonAcceptCreateTask.isClickable = false
            buttonAcceptCreateTask.setBackgroundColor(getColor(R.color.gray))
        } else {
            buttonAcceptCreateTask.isClickable = true
            buttonAcceptCreateTask.setBackgroundColor(getColor(R.color.completed))

        }
    }

    override fun showLoading() {
        showDialog()
    }

    override fun closeLoading() {
        closeDialog()
    }
}