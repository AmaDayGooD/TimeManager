package com.example.timemanager.ui.screens.users_top

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityUserTopBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.users_top.recycle_view.UsersTopListAdapter

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
    private lateinit var buttonFamily: Button
    private lateinit var buttonWorld: Button

    val adapter = UsersTopListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycleView = binding.recycleViewList
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        buttonFamily = binding.buttonFamily
        buttonWorld = binding.buttonWorld


        buttonFamily.setOnClickListener {
            presenter.requestListUsers(newFamily = true)
        }

        buttonWorld.setOnClickListener {
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