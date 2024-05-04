package com.example.timemanager.ui.screens.list_task

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.data.Condition
import com.example.timemanager.databinding.ActivityTasksBinding
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.list_task.recycle_view.OnItemClickListener
import com.example.timemanager.ui.screens.list_task.recycle_view.TaskListAdapter
import com.example.timemanager.ui.screens.my_task.MyTaskActivity.Companion.createIntentMyTask
import com.example.timemanager.ui.screens.profile.ProfileActivity.Companion.createIntentMainScreen

@RequiresApi(Build.VERSION_CODES.O)
class TasksActivity : BaseActivity(R.layout.activity_tasks), TasksView, OnItemClickListener {

    private lateinit var binding: ActivityTasksBinding

    override val presenter: TasksPresenter by providePresenter {
        TasksPresenter()
    }

    companion object {
        fun createIntentTaskActivity(context: Context): Intent {
            return Intent(context, TasksActivity::class.java)
        }
    }

    private lateinit var recyclerView: RecyclerView

    private lateinit var buttonChangeView: Button
    private lateinit var buttonProfile: CardView
    private val adapter = TaskListAdapter(this)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recycleViewList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonChangeView = binding.buttonChangeView
        buttonProfile = binding.buttonProfile

        buttonChangeView.setOnClickListener {
            presenter.changeViewList()
        }

        buttonProfile.setOnClickListener {
            startActivity(createIntentMainScreen(this))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.updateList()
    }

    override fun setTaskList(list: List<Task>, state: Boolean) {
        adapter.setList(list)
        if (state) binding.buttonChangeView.text = getString(R.string.show_all)
        else binding.buttonChangeView.text = getString(R.string.show_today)
        closeLoading()
    }

    override fun onClickOpenTask(taskId: Int) {
        startActivity(createIntentMyTask(this, taskId))
    }

    override fun setState(button: Button, state: Condition): Button{
        return button.apply {
            text = getString(state.textResId)
            setBackgroundColor(ContextCompat.getColor(this@TasksActivity, state.colorRes))
        }
    }

    override fun getResourcesString(idString: Int, vararg formatArgs: Any): String {
        return if (formatArgs.isNotEmpty()) {
            getString(idString, *formatArgs)
        } else {
            getString(idString)
        }
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }

}