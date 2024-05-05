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
    fun showDialog(context: Context) {
        dialog = Dialog(this, R.style.DialogFullscreen)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE) // Переместите вызов requestWindowFeature() перед setContentView()
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_loading)
        val window = dialog?.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.show()
    }

    private var infoDialog: Dialog? = null
    fun showInfoDialog(context: Context, title: String = "", text: String = "") {
        infoDialog = Dialog(this, R.style.DialogFullscreen)
        infoDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE) // Переместите вызов requestWindowFeature() перед setContentView()
        infoDialog?.setCancelable(false)
        infoDialog?.setContentView(R.layout.dialog_info)

        val infoTitle = infoDialog?.findViewById<TextView>(R.id.dialog_title)
        val infoText = infoDialog?.findViewById<TextView>(R.id.dialog_text)
        val buttonOk = infoDialog?.findViewById<Button>(R.id.button_ok)

        infoTitle?.text = title
        infoText?.text = text

        buttonOk?.setOnClickListener {
            infoDialog?.dismiss()
        }

        infoDialog?.show()
    }

    fun closeDialog() {
        dialog?.dismiss()
    }
}