package com.example.timemanager.ui.screens.authorization

import com.omega_r.base.mvp.views.OmegaMvpViewState
import com.omegar.mvp.CustomPresenterFactory
import com.omegar.mvp.MoxyReflector
import com.omegar.mvp.MvpDelegateHolder
import com.omegar.mvp.viewstate.ViewCommand
import com.omegar.mvp.viewstate.strategy.SingleStateStrategy
import com.omegar.mvp.viewstate.strategy.SkipStrategy
import kotlin.String
import kotlin.reflect.KClass

public open class AuthorizationMvpViewState<OMEGAVIEW : AuthorizationView> :
		OmegaMvpViewState<OMEGAVIEW>(), AuthorizationView {
	override fun requestGotoMainActivity() {
		apply(RequestGotoMainActivityCommand())
	}

	override fun requestGotoRegisterActivity() {
		apply(RequestGotoRegisterActivityCommand())
	}

	override fun showToast(text: String) {
		apply(ShowToastCommand(text))
	}

	override fun invalidPassword() {
		apply(InvalidPasswordCommand())
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
			MoxyReflector[AuthorizationPresenter::class] = {AuthorizationMvpViewState<AuthorizationView>() }}
	}

	private class RequestGotoMainActivityCommand<OMEGAVIEW : AuthorizationView>() :
			ViewCommand<OMEGAVIEW>("requestGotoMainActivity", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.requestGotoMainActivity()
		}

		override fun toString(): String = buildString("requestGotoMainActivity",)
	}

	private class RequestGotoRegisterActivityCommand<OMEGAVIEW : AuthorizationView>() :
			ViewCommand<OMEGAVIEW>("requestGotoRegisterActivity", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.requestGotoRegisterActivity()
		}

		override fun toString(): String = buildString("requestGotoRegisterActivity",)
	}

	private class ShowToastCommand<OMEGAVIEW : AuthorizationView>(
		public val text: String,
	) : ViewCommand<OMEGAVIEW>("showToast", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showToast(text)
		}

		override fun toString(): String = buildString("showToast","text",text)
	}

	private class InvalidPasswordCommand<OMEGAVIEW : AuthorizationView>() :
			ViewCommand<OMEGAVIEW>("invalidPassword", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.invalidPassword()
		}

		override fun toString(): String = buildString("invalidPassword",)
	}

	private class LogCommand<OMEGAVIEW : AuthorizationView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : AuthorizationView>() :
			ViewCommand<OMEGAVIEW>("showLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : AuthorizationView>() :
			ViewCommand<OMEGAVIEW>("closeLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> AuthorizationPresenter):
		CustomPresenterFactory<AuthorizationPresenter, AuthorizationView> {
	AuthorizationMvpViewState.Companion
	return CustomPresenterFactory<AuthorizationPresenter, AuthorizationView>(AuthorizationPresenter::class
			as KClass<AuthorizationPresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it)
			}
}
