package com.example.timemanager.ui.screens.my_children

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityMyChildrenBinding
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.my_children.recycle_view.ChildrenListAdapter

class MyChildrenActivity : BaseActivity(R.layout.activity_my_children), MyChildrenView {

    private lateinit var binding: ActivityMyChildrenBinding

    override val presenter: MyChildrenPresenter by providePresenter {
        MyChildrenPresenter()
    }

    companion object {
        fun createIntentMyChildren(context: Context): Intent {
            return Intent(context, MyChildrenActivity::class.java)
        }
    }

    private lateinit var dialog: Dialog

    private lateinit var buttonBack :ImageButton
    private lateinit var recycleView: RecyclerView
    private lateinit var buttonAddChild: Button

    private val adapter = ChildrenListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyChildrenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonBack = binding.buttonBack
        recycleView = binding.recycleViewList
        buttonAddChild = binding.buttonAddChild

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        buttonBack.setOnClickListener{
            finish()
        }

        buttonAddChild.setOnClickListener {
            showInputDialog()
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


        val buttonAddChild = dialog.findViewById<Button>(R.id.button_add_child)

        buttonAddChild.setOnClickListener {
            val login = dialog.findViewById<EditText>(R.id.edit_text_login).text.toString()
            val firstName = dialog.findViewById<EditText>(R.id.edit_text_first_name).text.toString()
            val lastName = dialog.findViewById<EditText>(R.id.edit_text_last_name).text.toString()

            if (login.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()) {
                presenter.addChild(login, firstName, lastName)
            } else {
                Toast.makeText(this@MyChildrenActivity, "Заполните все поля", Toast.LENGTH_LONG)
                    .show()
            }
        }
        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    override fun successfullyAdded() {
        Toast.makeText(this, getString(R.string.child_added), Toast.LENGTH_LONG).show()
        dialog.dismiss()
    }

    override fun filedAdded() {
        Toast.makeText(this, getString(R.string.filed_find), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}