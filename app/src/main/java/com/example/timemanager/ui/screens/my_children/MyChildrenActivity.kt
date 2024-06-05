package com.example.timemanager.ui.screens.my_children

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityMyChildrenBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.ScanActivity.Companion.createIntentScanActivity
import com.example.timemanager.ui.screens.awards.AwardsActivity
import com.example.timemanager.ui.screens.list_task.TasksActivity
import com.example.timemanager.ui.screens.my_children.recycle_view.ChildrenListAdapter
import com.example.timemanager.ui.screens.profile.ProfileActivity
import com.example.timemanager.ui.screens.users_top.UsersTopActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MyChildrenActivity : BaseActivity(R.layout.activity_my_children), MyChildrenView {

    private lateinit var binding: ActivityMyChildrenBinding
    private lateinit var scanActivityResultLauncher: ActivityResultLauncher<Intent>

    override val presenter: MyChildrenPresenter by providePresenter {
        MyChildrenPresenter()
    }

    companion object {
        fun createIntentMyChildren(context: Context): Intent {
            return Intent(context, MyChildrenActivity::class.java)
        }
        const val SCAN_REQUEST_CODE = 102
    }

    private lateinit var dialog: Dialog

    private lateinit var buttonAwards: MaterialCardView
    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonProfile: MaterialCardView
    private lateinit var buttonUserTop: MaterialCardView

    private lateinit var buttonBack: ImageButton
    private lateinit var recycleView: RecyclerView
    private lateinit var buttonAddChild: Button

    private val adapter = ChildrenListAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyChildrenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonListTasks = binding.buttonTasks
        buttonProfile = binding.buttonProfile
        buttonUserTop = binding.buttonStatistics
        buttonAwards = binding.buttonAwards

        buttonBack = binding.buttonBack
        recycleView = binding.recycleViewList
        buttonAddChild = binding.buttonAddChild

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        buttonBack.setOnClickListener {
            finish()
        }

        scanActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val scanResult = result.data?.getStringExtra("SCAN_RESULT")
                scanResult?.let { result ->
                    presenter.addChild(result)
                }
            }
        }

        buttonAddChild.setOnClickListener {
            showInputDialog()
        }

        buttonListTasks.setOnClickListener {
            startActivity(TasksActivity.createIntentTaskActivity(this))
        }

        buttonProfile.setOnClickListener {
            startActivity(ProfileActivity.createIntentMainScreen(this))
        }

        buttonUserTop.setOnClickListener {
            startActivity(UsersTopActivity.createIntentUsersTopScreen(this))
        }

        buttonAwards.setOnClickListener {
            startActivity(AwardsActivity.createIntentAwardsScreen(this))
        }

        presenter.getChild()
    }

    override fun setList(childList: List<Profile>) {
        adapter.setList(childList)
        closeLoading()
    }

    private fun showInputDialog() {
        dialog = Dialog(this, R.style.DialogStyle)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_add_child)

        val buttonFindByQrCode = dialog.findViewById<MaterialButton>(R.id.button_by_qr_code)
        val buttonFindByLogin = dialog.findViewById<MaterialButton>(R.id.button_by_login)
        val linearLayoutFindByLogin = dialog.findViewById<LinearLayout>(R.id.linear_layout_find_by_login)
        val editTextLogin = dialog.findViewById<EditText>(R.id.edit_text_login)
        val buttonAddChild = dialog.findViewById<Button>(R.id.button_add_child)

        buttonFindByQrCode.setOnClickListener {
            scanActivityResultLauncher.launch(createIntentScanActivity(this))
        }

        buttonFindByLogin.setOnClickListener {
            buttonFindByQrCode.visibility = View.GONE
            buttonFindByLogin.visibility = View.GONE
            linearLayoutFindByLogin.visibility = View.VISIBLE
            editTextLogin.isFocusable = true
        }

        buttonAddChild.setOnClickListener {
            val login = editTextLogin.text.toString()

            if (login.isNotEmpty())
                presenter.addChild(login)
             else
                Toast.makeText(this@MyChildrenActivity, R.string.empty_field, Toast.LENGTH_LONG)
                    .show()

        }
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCAN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val scanResult = data?.getStringExtra("SCAN_RESULT")
            Toast.makeText(this, "MyLog Scanned: $scanResult", Toast.LENGTH_LONG).show()
        }
    }

    override fun successfullyAdded() {
        Toast.makeText(this, getString(R.string.child_added), Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }

    override fun filedAdded() {
        Toast.makeText(this, getString(R.string.filed_find), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        showDialog()
    }

    override fun closeLoading() {
        closeDialog()
    }
}