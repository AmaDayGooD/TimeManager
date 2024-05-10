package com.example.timemanager.ui.screens.awards

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityAwardsBinding
import com.example.timemanager.entity.Award
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.awards.recycle_view.AwardListAdapter
import com.example.timemanager.ui.screens.awards.recycle_view.OnItemClickListener

class AwardsActivity : BaseActivity(R.layout.activity_awards), AwardsView, OnItemClickListener {

    private lateinit var binding: ActivityAwardsBinding

    override val presenter: AwardsPresenter by providePresenter {
        AwardsPresenter()
    }

    companion object {
        fun createIntentAwardsScreen(context: Context): Intent {
            return Intent(context, AwardsActivity::class.java)
        }
    }

    private lateinit var dialog: Dialog

    private lateinit var buttonCreateAward: Button
    private lateinit var recycleView: RecyclerView

    private lateinit var adapter: AwardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recycleView = binding.recycleViewAwardsList
        recycleView.layoutManager = LinearLayoutManager(this)

        buttonCreateAward = binding.buttonAddAward

        buttonCreateAward.setOnClickListener {
            showCreateAwardDialog()
        }
    }

    override fun setListAdapter(userBalance: Int) {
        adapter = AwardListAdapter(this, userBalance)
        recycleView.adapter = adapter
    }

    private fun showCreateAwardDialog() {
        dialog = Dialog(this, R.style.DialogStyle)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_create_award)

        val buttonCreateAward = dialog.findViewById<Button>(R.id.button_create_award)

        val editTextPrice = dialog.findViewById<EditText>(R.id.edit_text_price)
        val editTextName = dialog.findViewById<EditText>(R.id.edit_text_name)
        val editTextDescription = dialog.findViewById<EditText>(R.id.edit_text_description)

        buttonCreateAward.setOnClickListener {
            val price = editTextPrice.text.toString()
            val name = editTextName.text.toString()
            val description = editTextDescription.text.toString()
            if (price.isEmpty() || name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, getString(R.string.fill_all_field), Toast.LENGTH_SHORT).show()
            } else {
                presenter.createAward(name, description, price)
            }
        }

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }

    override fun resultCreatingAward(result: Boolean) {
        if (result) {
            Toast.makeText(this, getString(R.string.award_successfully_created), Toast.LENGTH_SHORT).show()
            presenter.updateListAward()
        } else {
            Toast.makeText(this, getString(R.string.award_failed_created), Toast.LENGTH_SHORT).show()
        }
    }

    override fun setAwardList(awardList: List<Award>) {
        adapter.setList(awardList)
    }

    override fun showLoading() {
        showDialog(this)
    }

    override fun closeLoading() {
        closeDialog()
    }
}