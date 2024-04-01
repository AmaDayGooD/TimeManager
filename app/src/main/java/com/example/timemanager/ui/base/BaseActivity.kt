package com.example.timemanager.ui.base

import android.util.Log
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
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
}