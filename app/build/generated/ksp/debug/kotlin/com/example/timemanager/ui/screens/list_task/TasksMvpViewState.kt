package com.example.timemanager.ui.screens.list_task

import com.example.timemanager.entity.Task
import com.omega_r.base.mvp.views.OmegaMvpViewState
import com.omegar.mvp.CustomPresenterFactory
import com.omegar.mvp.MoxyReflector
import com.omegar.mvp.MvpDelegateHolder
import com.omegar.mvp.viewstate.ViewCommand
import com.omegar.mvp.viewstate.strategy.AddToEndStrategy
import com.omegar.mvp.viewstate.strategy.SingleStateStrategy
import com.omegar.mvp.viewstate.strategy.SkipStrategy
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

public open class TasksMvpViewState<OMEGAVIEW : TasksView> : OmegaMvpViewState<OMEGAVIEW>(),
		TasksView {
	override fun setTaskList(list: List<Task>, state: Boolean) {
		apply(SetTaskListCommand(list, state))
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
			MoxyReflector[TasksPresenter::class] = {TasksMvpViewState<TasksView>() }}
	}

	private class SetTaskListCommand<OMEGAVIEW : TasksView>(
		public val list: List<Task>,
		public val state: Boolean,
	) : ViewCommand<OMEGAVIEW>("setTaskList", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.setTaskList(list, state)
		}

		override fun toString(): String = buildString("setTaskList","list",list, "state",state)
	}

	private class LogCommand<OMEGAVIEW : TasksView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : TasksView>() : ViewCommand<OMEGAVIEW>("showLoading",
			SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : TasksView>() : ViewCommand<OMEGAVIEW>("closeLoading",
			SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> TasksPresenter):
		CustomPresenterFactory<TasksPresenter, TasksView> {
	TasksMvpViewState.Companion
	return CustomPresenterFactory<TasksPresenter, TasksView>(TasksPresenter::class as
			KClass<TasksPresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it) }
}
