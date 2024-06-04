package com.example.timemanager.ui.screens.users_top

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityUserTopBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.awards.AwardsActivity
import com.example.timemanager.ui.screens.list_task.TasksActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity.Companion.createIntentMainScreen
import com.example.timemanager.ui.screens.users_top.recycle_view.UsersTopListAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class UsersTopActivity : BaseActivity(R.layout.activity_user_top), UsersTopView {

    private lateinit var binding: ActivityUserTopBinding

    override val presenter: UsersTopPresenter by providePresenter {
        UsersTopPresenter()
    }

    companion object {
        fun createIntentUsersTopScreen(context: Context): Intent {
            return Intent(context, UsersTopActivity::class.java)
        }
    }

    private lateinit var recycleView: RecyclerView
    private lateinit var buttonFamily: MaterialButton
    private lateinit var buttonWorld: MaterialButton

    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonProfile: MaterialCardView
    private lateinit var buttonAwards: MaterialCardView

    val adapter = UsersTopListAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonListTasks = binding.buttonTasks
        buttonProfile = binding.buttonProfile
        buttonAwards = binding.buttonAwards

        recycleView = binding.recycleViewList
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        buttonFamily = binding.buttonFamily
        buttonWorld = binding.buttonWorld

        buttonListTasks.setOnClickListener {
            startActivity(TasksActivity.createIntentTaskActivity(this))
        }

        buttonProfile.setOnClickListener {
            startActivity(createIntentMainScreen(this))
        }

        buttonAwards.setOnClickListener {
            startActivity(AwardsActivity.createIntentAwardsScreen(this))
        }

        buttonFamily.setOnClickListener {
            buttonFamily.strokeColor = ColorStateList.valueOf(getColor(R.color.semitransparent_gray))
            buttonWorld.strokeColor = ColorStateList.valueOf(getColor(R.color.white))
            presenter.requestListUsers(newFamily = true)
        }

        buttonWorld.setOnClickListener {
            buttonWorld.strokeColor = ColorStateList.valueOf(getColor(R.color.semitransparent_gray))
            buttonFamily.strokeColor = ColorStateList.valueOf(getColor(R.color.white))
            presenter.requestListUsers(newFamily = false)
        }

    }

    override fun setList(list: List<Profile>) {
        adapter.setList(list)
    }

    override fun showLoading() {
        showDialog()
    }

    override fun closeLoading() {
        closeDialog()
    }
}