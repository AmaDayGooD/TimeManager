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
import androidx.annotation.RequiresApi
import com.example.timemanager.R
import com.example.timemanager.data.Importance
import com.example.timemanager.databinding.ActivityCreateTaskBinding
import com.example.timemanager.ui.base.BaseActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Calendar

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
    private lateinit var buttonTaskStart: Button
    private lateinit var buttonTaskEnd: Button
    private lateinit var buttonAcceptCreateTask: Button

    var list = emptyList<String>()
    private var taskName: String = ""
    private var taskDescription: String = ""
    private var seriousness: Importance = Importance.Low
    private var award: String = ""
    private var dateTimeStart: LocalDateTime? = null
    private var dateTimeEnd: LocalDateTime? = null

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

        buttonTaskStart = binding.buttonSetTimeStart
        buttonTaskEnd = binding.buttonSetTimeEnd
        buttonAcceptCreateTask = binding.buttonAcceptNewTask

        buttonBack.setOnClickListener {
            finish()
        }

        buttonTaskStart.setOnClickListener {
            showDatePickerDialog(true)
        }

        buttonTaskEnd.setOnClickListener {
            showDatePickerDialog(false)
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
                dateTimeStart,
                dateTimeEnd
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

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    override fun showResultCreateTask(result: Boolean) {
        if (result) {
            showInfoDialog(this, getString(R.string.successfully), getString(R.string.successfully_created))
            finish()
        } else {
            showInfoDialog(this, getString(R.string.error), getString(R.string.failed_created))
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
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            showTimePickerDialog(isStart, selectedDate)
        }, year, month, dayOfMonth)

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
                dateTimeStart = LocalDateTime.of(selectedDate, selectedTime)
                buttonTaskStart.text = dateTimeStart.toString()
            } else {
                dateTimeEnd = LocalDateTime.of(selectedDate, selectedTime)
                buttonTaskEnd.text = dateTimeEnd.toString()
            }
            if (dateTimeStart.compare(localDataTime = dateTimeEnd)) {
                showInfoDialog(
                    this, getString(R.string.title_incorrect_start_end_data), getString(R.string.info_incorrect_start_end_data)
                )
                isError = true
            }
            setStateButton()
        }, hourOfDay, minute, true)

        timePickerDialog.show()
    }

    private fun setStateButton() {
        if (taskName.isNotEmpty() && taskDescription.isNotEmpty() && award.isNotEmpty() && dateTimeStart != null && dateTimeEnd != null) {
            buttonAcceptCreateTask.isClickable = true
            buttonAcceptCreateTask.setBackgroundColor(getColor(R.color.completed))
        } else {
            buttonAcceptCreateTask.isClickable = false
            buttonAcceptCreateTask.setBackgroundColor(getColor(R.color.gray))
        }
    }

    private fun LocalDateTime?.compare(localDataTime: LocalDateTime?): Boolean {
        if (this == null || localDataTime == null) return false
        val comparisonResult = this.compareTo(localDataTime)
        return when {
            comparisonResult < 0 -> false
            comparisonResult > 0 -> true
            else -> true
        }
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}