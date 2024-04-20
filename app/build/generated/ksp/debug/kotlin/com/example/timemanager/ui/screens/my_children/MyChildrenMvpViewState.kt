package com.example.timemanager.ui.screens.my_children

import com.example.timemanager.entity.Profile
import com.omega_r.base.mvp.views.OmegaMvpViewState
import com.omegar.mvp.CustomPresenterFactory
import com.omegar.mvp.MoxyReflector
import com.omegar.mvp.MvpDelegateHolder
import com.omegar.mvp.viewstate.ViewCommand
import com.omegar.mvp.viewstate.strategy.AddToEndStrategy
import com.omegar.mvp.viewstate.strategy.SingleStateStrategy
import com.omegar.mvp.viewstate.strategy.SkipStrategy
import kotlin.String
import kotlin.collections.List
import kotlin.reflect.KClass

public open class MyChildrenMvpViewState<OMEGAVIEW : MyChildrenView> :
		OmegaMvpViewState<OMEGAVIEW>(), MyChildrenView {
	override fun setList(childList: List<Profile>) {
		apply(SetListCommand(childList))
	}

	override fun successfullyAdded() {
		apply(SuccessfullyAddedCommand())
	}

	override fun filedAdded() {
		apply(FiledAddedCommand())
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
			MoxyReflector[MyChildrenPresenter::class] = {MyChildrenMvpViewState<MyChildrenView>() }}
	}

	private class SetListCommand<OMEGAVIEW : MyChildrenView>(
		public val childList: List<Profile>,
	) : ViewCommand<OMEGAVIEW>("setList", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.setList(childList)
		}

		override fun toString(): String = buildString("setList","childList",childList)
	}

	private class SuccessfullyAddedCommand<OMEGAVIEW : MyChildrenView>() :
			ViewCommand<OMEGAVIEW>("successfullyAdded", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.successfullyAdded()
		}

		override fun toString(): String = buildString("successfullyAdded",)
	}

	private class FiledAddedCommand<OMEGAVIEW : MyChildrenView>() :
			ViewCommand<OMEGAVIEW>("filedAdded", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.filedAdded()
		}

		override fun toString(): String = buildString("filedAdded",)
	}

	private class LogCommand<OMEGAVIEW : MyChildrenView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : MyChildrenView>() :
			ViewCommand<OMEGAVIEW>("showLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : MyChildrenView>() :
			ViewCommand<OMEGAVIEW>("closeLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> MyChildrenPresenter):
		CustomPresenterFactory<MyChildrenPresenter, MyChildrenView> {
	MyChildrenMvpViewState.Companion
	return CustomPresenterFactory<MyChildrenPresenter, MyChildrenView>(MyChildrenPresenter::class as
			KClass<MyChildrenPresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it) }
}
