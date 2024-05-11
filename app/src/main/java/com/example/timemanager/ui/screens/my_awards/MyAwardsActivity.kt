package com.example.timemanager.ui.screens.my_awards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityMyAwardsBinding
import com.example.timemanager.entity.Award
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.my_awards.my_awards_recycle_view.MyAwardsListAdapter

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

    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: MyAwardsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAwardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycleView = binding.recycleViewMyAwardsList
        recycleView.layoutManager = LinearLayoutManager(this)
    }

    override fun setListAdapter() {
        adapter = MyAwardsListAdapter()
        recycleView.adapter = adapter
    }

    override fun setList(list: List<Award>) {
        adapter.setList(list)
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}