package com.example.timemanager.ui.screens.authorization

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ContentInfoCompat.Flags
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityAuthorizationBinding
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity.Companion.createIntentMainScreen
import com.example.timemanager.ui.screens.registration.RegistrationActivity.Companion.createIntentRegistrationScreen

class AuthorizationActivity : BaseActivity(R.layout.activity_authorization), AuthorizationView {

    private lateinit var binding: ActivityAuthorizationBinding

    override val presenter: AuthorizationPresenter by providePresenter {
        AuthorizationPresenter()
    }

    companion object {
        fun createIntentAuthScreen(context: Context): Intent {
            return Intent(context, AuthorizationActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        }
    }

    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: TextView
    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonLogin = binding.buttonAuthorization
        buttonRegister = binding.buttonRegistration
        editTextLogin = binding.editTextLogin
        editTextPassword = binding.editTextPassword

        buttonLogin.setOnClickListener {
            val textLogin = binding.editTextLogin.text.toString().trim()
            val textPassword = binding.editTextPassword.text.toString().trim()
            presenter.checkEmptyTextField(textLogin, textPassword)
        }

        buttonRegister.setOnClickListener {
            requestGotoRegisterActivity()
        }
    }

    override fun requestGotoMainActivity() {
        startActivity(createIntentMainScreen(this))
    }

    override fun requestGotoRegisterActivity() {
        startActivity(createIntentRegistrationScreen(this))
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