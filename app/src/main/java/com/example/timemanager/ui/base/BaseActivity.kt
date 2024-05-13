package com.example.timemanager.ui.base

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.example.timemanager.R
import com.omega_r.base.components.OmegaActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.base.mvp.views.OmegaView

abstract class BaseActivity : OmegaActivity, BaseView {
    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract override val presenter: OmegaPresenter<out OmegaView>

    override fun log(message: String) {
        Log.d("MyLog", "$message")
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private var dialog: Dialog? = null
    fun showDialog() {
        dialog = Dialog(this, R.style.DialogFullscreen)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        val window = dialog?.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.show()
    }

    fun showInfoDialog(title: String = "", text: String = "", cancelable: Boolean = false) {
        val infoDialog = Dialog(this, R.style.DialogFullscreen)
        infoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        infoDialog.setCancelable(cancelable)
        infoDialog.setContentView(R.layout.dialog_info)

        val infoTitle = infoDialog.findViewById<TextView>(R.id.dialog_title)
        val infoText = infoDialog.findViewById<TextView>(R.id.dialog_text)
        val buttonOk = infoDialog.findViewById<Button>(R.id.button_ok)

        infoTitle?.text = title
        infoText?.text = text

        buttonOk?.setOnClickListener {
            infoDialog.dismiss()
        }

        infoDialog.show()
    }

    fun showDialogWithChoice(
        title: String = getString(R.string.attention),
        text: String = getString(R.string.do_you_agree),
        onClickPositive: () -> Unit = {},
        onClickNegative: () -> Unit = {},
    ) {
        val dialogAcceptTask = Dialog(this, R.style.DialogStyle)
        dialogAcceptTask.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogAcceptTask.setCancelable(false)
        dialogAcceptTask.setContentView(R.layout.dialog_accept_task)

        val textViewTitle = dialogAcceptTask.findViewById<TextView>(R.id.text_view_title)
        val textViewText = dialogAcceptTask.findViewById<TextView>(R.id.text_view_text)
        val buttonPositive = dialogAcceptTask.findViewById<Button>(R.id.button_positive)
        val buttonNegative = dialogAcceptTask.findViewById<Button>(R.id.button_negative)

        textViewTitle.text = title
        textViewText.text = text

        buttonPositive.setOnClickListener {
            onClickPositive()
            dialogAcceptTask.dismiss()
        }

        buttonNegative.setOnClickListener {
            onClickNegative()
            dialogAcceptTask.dismiss()
        }

        dialogAcceptTask.show()

    }

    fun closeDialog() {
        dialog?.dismiss()
    }
}