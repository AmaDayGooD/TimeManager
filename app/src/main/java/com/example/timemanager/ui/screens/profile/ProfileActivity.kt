package com.example.timemanager.ui.screens.profile

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityProfileBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.authorization.AuthorizationActivity.Companion.createIntentAuthScreen
import com.example.timemanager.ui.screens.list_task.TasksActivity.Companion.createIntentTaskActivity
import com.google.android.material.card.MaterialCardView

class ProfileActivity : BaseActivity(R.layout.activity_profile), ProfileView {

    private lateinit var binding: ActivityProfileBinding

    override val presenter: ProfilePresenter by providePresenter {
        ProfilePresenter()
    }

    private lateinit var textViewBalance: TextView
    private lateinit var textViewFirstName: TextView
    private lateinit var textViewLastName: TextView
    private lateinit var buttonEditProfile: Button
    private lateinit var buttonAcceptEdit: Button
    private lateinit var buttonExit: Button
    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonStatistics: MaterialCardView
    private lateinit var buttonLeaderBoard: MaterialCardView

    companion object {
        fun createIntentMainScreen(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textViewBalance = binding.textviewBalance
        buttonEditProfile = binding.buttonEditProfile
        buttonExit = binding.buttonExit
        textViewFirstName = binding.textviewFirstName
        textViewLastName = binding.textviewLastName
        buttonAcceptEdit = binding.buttonAcceptEdit

        buttonListTasks = binding.buttonTasks
        buttonStatistics = binding.buttonStatistics
        buttonLeaderBoard = binding.buttonLeaderBoard

        presenter.getProfile()
        //presenter.getTasks()

        buttonListTasks.setOnClickListener {
            startActivity(createIntentTaskActivity(this))
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

        buttonAcceptEdit.setOnClickListener {
            val firstName = textViewFirstName.text.toString()
            val lastName = textViewLastName.text.toString()
            presenter.editProfile(firstName, lastName)
        }

        buttonExit.setOnClickListener {
            presenter.requestGotoAuthorization()
        }
    }

    override fun setProfile(profile: Profile) {
        val username = profile.username?.split(" ")
        textViewFirstName.text = username?.get(0) ?: ""
        textViewLastName.text = username?.get(1) ?: ""
        textViewBalance.text = (profile.count ?: "0") as CharSequence?
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
}