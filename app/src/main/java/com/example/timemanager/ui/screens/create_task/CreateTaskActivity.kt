package com.example.timemanager.ui.screens.create_task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityCreateTaskBinding
import com.example.timemanager.ui.base.BaseActivity

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

    private lateinit var spinnerChild: Spinner
    private lateinit var titleTask: EditText
    private lateinit var descriptionTask: EditText

    var list = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinnerChild = binding.spinnerChilds
        titleTask = binding.textViewNameTask
        descriptionTask = binding.textViewDescriptionTask

    }

    private fun initSpinner() {
        val spinnerAdapter = ArrayAdapter(this@CreateTaskActivity, R.layout.spinner_row, R.id.text_view_text, list)
        spinnerChild.adapter = spinnerAdapter
        spinnerChild.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
    }

    override fun setNameChild(nameChild: List<String>) {
        list = nameChild
        initSpinner()
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}