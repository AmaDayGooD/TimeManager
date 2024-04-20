package com.example.timemanager.ui.screens.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityRegistrationBinding
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity.Companion.createIntentMainScreen
import com.google.android.material.card.MaterialCardView

class RegistrationActivity : BaseActivity(R.layout.activity_registration), RegistrationView {

    private lateinit var binding: ActivityRegistrationBinding

    enum class EmptyField { ALL, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD }

    override val presenter: RegistrationPresenter by providePresenter {
        RegistrationPresenter()
    }

    companion object {
        fun createIntentRegistrationScreen(context: Context): Intent {
            return Intent(context, RegistrationActivity::class.java)
        }
    }

    private lateinit var buttonBack: ImageButton

    private lateinit var linearLayoutChoiceRole: LinearLayout
    private lateinit var buttonParentRole: MaterialCardView
    private lateinit var buttonChildrenRole: MaterialCardView

    private lateinit var buttonContinueRegistration: Button
    private lateinit var buttonRegistration: Button

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var borderFirstName: MaterialCardView
    private lateinit var borderLastName: MaterialCardView
    private lateinit var borderLogin: MaterialCardView
    private lateinit var borderPassword: MaterialCardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonBack = binding.buttonBack

        linearLayoutChoiceRole = binding.linearLayoutChoiceRole
        buttonParentRole = binding.cardParentRole
        buttonChildrenRole = binding.cardChildrenRole

        buttonContinueRegistration = binding.buttonContinueRegistration
        buttonRegistration = binding.buttonRegistration

        firstNameEditText = binding.editTextFirstName
        lastNameEditText = binding.editTextLastName
        loginEditText = binding.editTextLogin
        passwordEditText = binding.editTextPassword

        borderFirstName = binding.borderFirstName
        borderLastName = binding.borderLastName
        borderLogin = binding.borderLogin
        borderPassword = binding.borderPassword

        setEditTextWatcher(firstNameEditText, borderFirstName)
        setEditTextWatcher(lastNameEditText, borderLastName)
        setEditTextWatcher(loginEditText, borderLogin)
        setEditTextWatcher(passwordEditText, borderPassword)

        buttonBack.setOnClickListener{
            finish()
        }

        buttonParentRole.setOnClickListener {
            presenter.onChoiceParentRole()
        }

        buttonChildrenRole.setOnClickListener {
            presenter.onChildRole()
        }

        buttonContinueRegistration.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            presenter.onCheckFirstAndLastNames(firstName, lastName)
        }

        buttonRegistration.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.onCheckLoginAndPassword(login, password)
        }
    }

    override fun closeChoiceRole(){
        binding.linearLayoutChoiceRole.visibility = View.GONE
        binding.linearLayoutTextFields.visibility = View.VISIBLE
    }

    override fun emptyFirstAndLastNames(emptyField: String) {
        when (emptyField) {
            EmptyField.FIRST_NAME.toString() -> {
                borderFirstName.strokeColor = this.getColor(R.color.error)
            }
            EmptyField.LAST_NAME.toString() -> {
                borderLastName.strokeColor = this.getColor(R.color.error)
            }
            EmptyField.ALL.toString() -> {
                borderFirstName.strokeColor = this.getColor(R.color.error)
                borderLastName.strokeColor = this.getColor(R.color.error)
            }
        }
        Toast.makeText(this, getString(R.string.fill_all_field), Toast.LENGTH_SHORT).show()
    }

    private fun setEditTextWatcher(editText: EditText, border: MaterialCardView) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Меняем цвет stroke обратно, если EditText больше не пустой
                if (!s.isNullOrEmpty()) {
                    border.strokeColor =
                        this@RegistrationActivity.getColor(R.color.main) // Любой цвет, который хотите использовать
                }
            }
        })
    }

    override fun successfulEnterFirstAndLastName() {
        buttonContinueRegistration.visibility = View.GONE
        buttonRegistration.visibility = View.VISIBLE
        borderLogin.visibility = View.VISIBLE
        borderPassword.visibility = View.VISIBLE
    }

    override fun emptyLoginAndPassword(emptyField: String) {
        when (emptyField) {
            EmptyField.LOGIN.toString() -> {
                borderLogin.strokeColor = this.getColor(R.color.error)
            }
            EmptyField.PASSWORD.toString() -> {
                borderPassword.strokeColor = this.getColor(R.color.error)
            }
            EmptyField.ALL.toString() -> {
                borderLogin.strokeColor = this.getColor(R.color.error)
                borderPassword.strokeColor = this.getColor(R.color.error)
            }
        }
        Toast.makeText(this, getString(R.string.fill_all_field), Toast.LENGTH_SHORT).show()
    }

    override fun successfulRegistration() {
        Toast.makeText(
            this,
            getString(R.string.registration_completed_successfully),
            Toast.LENGTH_SHORT
        ).show()
        startActivity(createIntentMainScreen(this))
    }

    override fun failedRegistration(text: String) {
        Toast.makeText(this, getString(R.string.error_has_occurred, text), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}