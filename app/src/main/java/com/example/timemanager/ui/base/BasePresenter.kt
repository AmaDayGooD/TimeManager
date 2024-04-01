package com.example.timemanager.ui.base

import android.util.Log
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.base.mvp.views.OmegaView
import kotlinx.coroutines.CompletionHandlerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BasePresenter<View: OmegaView>: OmegaPresenter<View>(), CoroutineScope {
    override fun onDestroy() {
        job.cancel()
    }

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + job

    protected open fun log(message: String){
        Log.d("MyLog", message)
    }
}