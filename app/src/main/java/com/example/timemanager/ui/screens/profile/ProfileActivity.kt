package com.example.timemanager.ui.screens.profile

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityProfileBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.authorization.AuthorizationActivity.Companion.createIntentAuthScreen
import com.example.timemanager.ui.screens.awards.AwardsActivity.Companion.createIntentAwardsScreen
import com.example.timemanager.ui.screens.create_task.CreateTaskActivity.Companion.createIntentCreateTaskActivity
import com.example.timemanager.ui.screens.list_task.TasksActivity.Companion.createIntentTaskActivity
import com.example.timemanager.ui.screens.my_awards.MyAwardsActivity.Companion.createIntentMyAwardsScreen
import com.example.timemanager.ui.screens.my_children.MyChildrenActivity.Companion.createIntentMyChildren
import com.google.android.material.card.MaterialCardView

class ProfileActivity : BaseActivity(R.layout.activity_profile), ProfileView {

    private lateinit var binding: ActivityProfileBinding

    override val presenter: ProfilePresenter by providePresenter {
        ProfilePresenter()
    }

    private lateinit var labelMyBalance: TextView
    private lateinit var textViewBalance: TextView
    private lateinit var textViewFirstName: TextView
    private lateinit var textViewLastName: TextView
    private lateinit var buttonEditProfile: Button
    private lateinit var buttonAcceptEdit: Button
    private lateinit var buttonExit: Button
    private lateinit var buttonMyAwards: Button

    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonStatistics: MaterialCardView
    private lateinit var buttonAwards: MaterialCardView
    private lateinit var buttonMyChildren: Button
    private lateinit var buttonCreateNewTask: Button

    companion object {
        fun createIntentMainScreen(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        labelMyBalance = binding.labelMyBalance
        textViewBalance = binding.textviewBalance
        buttonEditProfile = binding.buttonEditProfile
        buttonExit = binding.buttonExit
        textViewFirstName = binding.textviewFirstName
        textViewLastName = binding.textviewLastName
        buttonAcceptEdit = binding.buttonAcceptEdit
        buttonMyChildren = binding.buttonMyChildren
        buttonCreateNewTask = binding.buttonCreateNewTask
        buttonMyAwards = binding.buttonMyAwards

        buttonListTasks = binding.buttonTasks
        buttonStatistics = binding.buttonStatistics
        buttonAwards = binding.buttonAwards


        buttonMyChildren.setOnClickListener {
            startActivity(createIntentMyChildren(this))
        }

        buttonCreateNewTask.setOnClickListener {
            startActivity(createIntentCreateTaskActivity(this))
        }

        buttonListTasks.setOnClickListener {
            startActivity(createIntentTaskActivity(this))
        }

        buttonAwards.setOnClickListener {
            startActivity(createIntentAwardsScreen(this))
        }

        buttonEditProfile.setOnClickListener {
            buttonEditProfile.visibility = View.GONE
            buttonAcceptEdit.visibility = View.VISIBLE
            textViewFirstName.isEnabled = true
            textViewFirstName.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
            textViewLastName.isEnabled = true
            textViewLastName.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
        }

        buttonMyAwards.setOnClickListener {
            startActivity(createIntentMyAwardsScreen(this))
        }

        buttonAcceptEdit.setOnClickListener {
            val firstName = textViewFirstName.text.toString()
            val lastName = textViewLastName.text.toString()
            presenter.editProfile(firstName, lastName)
        }

        buttonExit.setOnClickListener {
            presenter.requestGotoAuthorization()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getProfile()
    }

    override fun setProfile(profile: Profile) {
        val username = profile.username?.split(" ")
        textViewFirstName.text = username?.get(0).orEmpty()
        textViewLastName.text = username?.get(1).orEmpty()
        if (presenter.isParent()) {
            labelMyBalance.text = getString(R.string.paren)
        } else {
            buttonCreateNewTask.visibility = View.GONE
            buttonMyChildren.visibility = View.GONE
            textViewBalance.visibility = View.VISIBLE
            buttonMyAwards.visibility = View.VISIBLE
            textViewBalance.text = (profile.count ?: 0).toString()
        }
        closeLoading()
    }

    override fun setDefaultVisibleButton() {
        buttonEditProfile.visibility = View.VISIBLE
        buttonAcceptEdit.visibility = View.GONE
        textViewFirstName.isEnabled = false
        textViewLastName.isEnabled = false
        textViewFirstName.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.transparent))
        textViewLastName.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.transparent))
    }

    override fun gotoAuthorization() {
        startActivity(createIntentAuthScreen(this))
    }

    override fun showToast(text: String) {
        showToast(this, text)
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}