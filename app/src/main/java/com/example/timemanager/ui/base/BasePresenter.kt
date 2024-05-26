package com.example.timemanager.ui.base

import android.util.Log
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.base.mvp.views.OmegaView
import com.omega_r.libs.omegatypes.toText
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BasePresenter<View: OmegaView>: OmegaPresenter<View>(), CoroutineScope {
    override fun onDestroy() {
        job.cancel()
    }
    companion object {
        const val PREFIX_TOKEN = "Bearer"
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
            viewState.showMessage(throwable.message?.toText() ?: "Какая то ошибка".toText())
            log("Coroutine exception: $throwable")
            throwable.printStackTrace()
            // Здесь можно реализовать логирование, отправку отчетов об ошибках и т.д.
        }
    }

}