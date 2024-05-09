package com.example.timemanager.ui.screens.awards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityAwardsBinding
import com.example.timemanager.ui.base.BaseActivity

class AwardsActivity : BaseActivity(R.layout.activity_awards), AwardsView {

    private lateinit var binding: ActivityAwardsBinding

    override val presenter: AwardsPresenter by providePresenter{
        AwardsPresenter()
    }

    companion object {
        fun createIntentAwardsScreen(context: Context): Intent {
            return Intent(context, AwardsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}