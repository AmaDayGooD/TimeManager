package com.example.timemanager.ui.base

import android.provider.ContactsContract.Profile
import android.util.Log
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.base.mvp.views.OmegaView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BasePresenter<View: OmegaView>: OmegaPresenter<View>(), CoroutineScope {
    override fun onDestroy() {
        job.cancel()
    }

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main.immediate + job + coroutineExceptionHandler()

    protected open fun log(message: String){
        Log.d("MyLog", message)
    }

    private fun coroutineExceptionHandler(): CoroutineContext {
        return kotlinx.coroutines.CoroutineExceptionHandler { _, throwable ->
            // Обработка ошибки здесь
            log("Coroutine exception: ${throwable.localizedMessage}")
            throwable.printStackTrace()
            // Здесь можно реализовать логирование, отправку отчетов об ошибках и т.д.
        }
    }

}