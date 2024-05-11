package com.example.timemanager.ui.screens.list_task

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
import com.example.timemanager.ui.screens.awards.AwardsActivity
import com.example.timemanager.ui.screens.list_task.recycle_view.OnItemClickListener
import com.example.timemanager.ui.screens.list_task.recycle_view.TaskListAdapter
import com.example.timemanager.ui.screens.my_task.MyTaskActivity.Companion.createIntentMyTask
import com.example.timemanager.ui.screens.profile.ProfileActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity.Companion.createIntentMainScreen
import com.google.android.material.card.MaterialCardView

@RequiresApi(Build.VERSION_CODES.O)
class TasksActivity : BaseActivity(R.layout.activity_tasks), TasksView, OnItemClickListener {

    private lateinit var binding: ActivityTasksBinding

    override val presenter: TasksPresenter by providePresenter {
        TasksPresenter()
    }

    companion object {
        fun createIntentTaskActivity(context: Context): Intent {
            return Intent(context, TasksActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }

    private lateinit var buttonAwards: MaterialCardView
    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonProfile: MaterialCardView
    private lateinit var buttonStatistics: MaterialCardView

    private lateinit var recyclerView: RecyclerView


    private lateinit var buttonSortField: ImageView
    private lateinit var buttonSortOrder: ImageView
    private val adapter = TaskListAdapter(this)


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)


        buttonProfile = binding.buttonProfile
        buttonAwards = binding.buttonAwards
        buttonStatistics = binding.buttonStatistics
        recyclerView = binding.recycleViewList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonSortField = binding.buttonSortField
        buttonSortOrder = binding.buttonSortOrder

        buttonSortField.setOnClickListener {
            presenter.changeSortField()
        }

        buttonSortOrder.setOnClickListener {
            presenter.changeOrder()
        }

        buttonProfile.setOnClickListener {
            startActivity(createIntentMainScreen(this))
        }

        buttonStatistics.setOnClickListener {
            // startActivity(StatisticsActivity.createIntentStatisticsScreen(this))
        }

        buttonAwards.setOnClickListener {
            startActivity(AwardsActivity.createIntentAwardsScreen(this))
        }
    }

    override fun setIconSortField(state: StateSortField) {
        buttonSortField.setImageResource(state.icon)
    }

    override fun setIconSortOrder(state: StateOrder) {
        buttonSortOrder.setImageResource(state.icon)
    }

    enum class StateSortField(val icon: Int) {
        START_DATE_TIME(R.drawable.ic_timer),
        TASK_NAME(R.drawable.ic_list_light),
        AWARD(R.drawable.ic_fire_stroke),
        STATUS(R.drawable.ic_bookmark),
        IMPORTANCE(R.drawable.ic_lightning_40x40)
    }

    enum class StateOrder(val icon: Int) {
        ASC(R.drawable.ic_filter_asc),
        DESC(R.drawable.ic_filter_desc)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateList()
    }

    override fun setTaskList(list: List<Task>) {
        adapter.setList(list)
        closeLoading()
    }

    override fun onClickOpenTask(taskId: Int) {
        startActivity(createIntentMyTask(this, taskId))
    }

    override fun setState(button: Button, state: Condition): Button {
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