package com.example.timemanager.ui.screens.my_task

import com.example.timemanager.entity.Task
import com.omega_r.base.mvp.views.OmegaMvpViewState
import com.omegar.mvp.CustomPresenterFactory
import com.omegar.mvp.MoxyReflector
import com.omegar.mvp.MvpDelegateHolder
import com.omegar.mvp.viewstate.ViewCommand
import com.omegar.mvp.viewstate.strategy.AddToEndStrategy
import com.omegar.mvp.viewstate.strategy.SingleStateStrategy
import com.omegar.mvp.viewstate.strategy.SkipStrategy
import kotlin.String
import kotlin.reflect.KClass

public open class MyTaskMvpViewState<OMEGAVIEW : MyTaskView> : OmegaMvpViewState<OMEGAVIEW>(),
		MyTaskView {
	override fun setTaskInfo(task: Task?) {
		apply(SetTaskInfoCommand(task))
	}

	override fun log(message: String) {
		apply(LogCommand(message))
	}

	override fun showLoading() {
		apply(ShowLoadingCommand())
	}

	override fun closeLoading() {
		apply(CloseLoadingCommand())
	}

	public companion object {
		init {
			MoxyReflector[MyTaskPresenter::class] = {MyTaskMvpViewState<MyTaskView>() }}
	}

	private class SetTaskInfoCommand<OMEGAVIEW : MyTaskView>(
		public val task: Task?,
	) : ViewCommand<OMEGAVIEW>("setTaskInfo", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.setTaskInfo(task)
		}

		override fun toString(): String = buildString("setTaskInfo","task",task)
	}

	private class LogCommand<OMEGAVIEW : MyTaskView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : MyTaskView>() : ViewCommand<OMEGAVIEW>("showLoading",
			SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : MyTaskView>() :
			ViewCommand<OMEGAVIEW>("closeLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> MyTaskPresenter):
		CustomPresenterFactory<MyTaskPresenter, MyTaskView> {
	MyTaskMvpViewState.Companion
	return CustomPresenterFactory<MyTaskPresenter, MyTaskView>(MyTaskPresenter::class as
			KClass<MyTaskPresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it) }
}
