package com.example.timemanager.ui.screens.my_awards

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityMyAwardsBinding
import com.example.timemanager.entity.Award
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.awards.AwardsActivity
import com.example.timemanager.ui.screens.list_task.TasksActivity
import com.example.timemanager.ui.screens.my_awards.my_awards_recycle_view.MyAwardsListAdapter
import com.example.timemanager.ui.screens.profile.ProfileActivity
import com.google.android.material.card.MaterialCardView

class MyAwardsActivity : BaseActivity(R.layout.activity_my_awards), MyAwardsView {

    lateinit var binding: ActivityMyAwardsBinding

    override val presenter: MyAwardsPresenter by providePresenter {
        MyAwardsPresenter()
    }

    companion object {
        fun createIntentMyAwardsScreen(context: Context): Intent {
            return Intent(context, MyAwardsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }

    private lateinit var buttonBack: ImageButton
    private lateinit var buttonAwards: MaterialCardView
    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonProfile: MaterialCardView
    private lateinit var buttonStatistics: MaterialCardView

    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: MyAwardsListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAwardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonBack = binding.buttonBack
        buttonAwards = binding.buttonAwards
        buttonListTasks = binding.buttonTasks
        buttonProfile = binding.buttonProfile
        buttonStatistics = binding.buttonStatistics

        recycleView = binding.recycleViewMyAwardsList
        recycleView.layoutManager = LinearLayoutManager(this)

        buttonBack.setOnClickListener {
            finish()
        }

        buttonListTasks.setOnClickListener {
            startActivity(TasksActivity.createIntentTaskActivity(this))
        }

        buttonProfile.setOnClickListener {
            startActivity(ProfileActivity.createIntentMainScreen(this))
        }

        buttonAwards.setOnClickListener {
            startActivity(AwardsActivity.createIntentAwardsScreen(this))
        }
    }

    override fun setListAdapter() {
        adapter = MyAwardsListAdapter()
        recycleView.adapter = adapter
    }

    override fun setList(list: List<Award>) {
        adapter.setList(list)
    }

    override fun showLoading() {
        showDialog()
    }

    override fun closeLoading() {
        closeDialog()
    }
}