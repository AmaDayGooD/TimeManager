package com.example.timemanager.ui.screens.awards

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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R
import com.example.timemanager.databinding.ActivityAwardsBinding
import com.example.timemanager.entity.Award
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseActivity
import com.example.timemanager.ui.screens.awards.recycle_view.AwardListAdapter
import com.example.timemanager.ui.screens.awards.recycle_view.OnItemClickListener
import com.example.timemanager.ui.screens.list_task.TasksActivity
import com.example.timemanager.ui.screens.profile.ProfileActivity
import com.google.android.material.card.MaterialCardView
import com.example.timemanager.ui.screens.awards.AwardsPresenter.AwardErrors as AwardErrors

class AwardsActivity : BaseActivity(R.layout.activity_awards), AwardsView, OnItemClickListener {

    private lateinit var binding: ActivityAwardsBinding

    override val presenter: AwardsPresenter by providePresenter {
        AwardsPresenter()
    }

    companion object {
        fun createIntentAwardsScreen(context: Context): Intent {
            return Intent(context, AwardsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }

    private lateinit var buttonBack: ImageButton
    private lateinit var buttonAwards: MaterialCardView
    private lateinit var buttonListTasks: MaterialCardView
    private lateinit var buttonProfile: MaterialCardView
    private lateinit var buttonStatistics: MaterialCardView

    private lateinit var dialog: Dialog

    private lateinit var buttonCreateAward: Button
    private lateinit var recycleView: RecyclerView

    private lateinit var adapter: AwardListAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonBack = binding.buttonBack
        buttonProfile = binding.buttonProfile
        buttonAwards = binding.buttonAwards
        buttonStatistics = binding.buttonStatistics
        buttonListTasks = binding.buttonTasks

        buttonBack.setOnClickListener {
            finish()
        }

        buttonListTasks.setOnClickListener {
            startActivity(TasksActivity.createIntentTaskActivity(this))
        }

        buttonProfile.setOnClickListener {
            startActivity(ProfileActivity.createIntentMainScreen(this))
        }

        buttonStatistics.setOnClickListener {
            // startActivity(StatisticsActivity.createIntentStatisticsScreen(this))
        }

        recycleView = binding.recycleViewAwardsList
        recycleView.layoutManager = LinearLayoutManager(this)

        buttonCreateAward = binding.buttonAddAward

        buttonCreateAward.setOnClickListener {
            showCreateAwardDialog()
        }
    }

    override fun setRole(isParent: Boolean) {
        buttonCreateAward.visibility = if (isParent) View.VISIBLE else View.GONE
    }

    override fun setListAdapter(profile: Profile?) {
        if (profile == null) return
        adapter = AwardListAdapter(this, profile)
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
        showDialog()
    }

    override fun closeLoading() {
        closeDialog()
    }

    override fun onClickGetAward(awardId: Int) {
        showDialogWithChoice(
            title = getString(R.string.get_award),
            text = getString(R.string.for_you_work),
            onClickPositive = { presenter.getAward(awardId) },
            onClickNegative = {}
        )
    }

    override fun showDialogError(error: AwardErrors) {
        when (error) {
            AwardErrors.LACK_OFF_FIRE -> {
                showInfoDialog(title = getString(R.string.attention), text = getString(error.stringId))
            }

            AwardErrors.ALREADY_RECEIVED -> {
                showInfoDialog(title = getString(R.string.attention), text = getString(error.stringId))
            }
        }
    }
}