package com.example.timemanager.ui.screens.registration

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

public open class RegistrationMvpViewState<OMEGAVIEW : RegistrationView> :
		OmegaMvpViewState<OMEGAVIEW>(), RegistrationView {
	override fun successfulRegistration() {
		apply(SuccessfulRegistrationCommand())
	}

	override fun failedRegistration(text: String) {
		apply(FailedRegistrationCommand(text))
	}

	override fun emptyFirstAndLastNames(emptyField: String) {
		apply(EmptyFirstAndLastNamesCommand(emptyField))
	}

	override fun successfulEnterFirstAndLastName() {
		apply(SuccessfulEnterFirstAndLastNameCommand())
	}

	override fun emptyLoginAndPassword(emptyField: String) {
		apply(EmptyLoginAndPasswordCommand(emptyField))
	}

	override fun closeChoiceRole() {
		apply(CloseChoiceRoleCommand())
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
			MoxyReflector[RegistrationPresenter::class] = {RegistrationMvpViewState<RegistrationView>() }}
	}

	private class SuccessfulRegistrationCommand<OMEGAVIEW : RegistrationView>() :
			ViewCommand<OMEGAVIEW>("successfulRegistration", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.successfulRegistration()
		}

		override fun toString(): String = buildString("successfulRegistration",)
	}

	private class FailedRegistrationCommand<OMEGAVIEW : RegistrationView>(
		public val text: String,
	) : ViewCommand<OMEGAVIEW>("failedRegistration", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.failedRegistration(text)
		}

		override fun toString(): String = buildString("failedRegistration","text",text)
	}

	private class EmptyFirstAndLastNamesCommand<OMEGAVIEW : RegistrationView>(
		public val emptyField: String,
	) : ViewCommand<OMEGAVIEW>("emptyFirstAndLastNames", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.emptyFirstAndLastNames(emptyField)
		}

		override fun toString(): String = buildString("emptyFirstAndLastNames","emptyField",emptyField)
	}

	private class SuccessfulEnterFirstAndLastNameCommand<OMEGAVIEW : RegistrationView>() :
			ViewCommand<OMEGAVIEW>("successfulEnterFirstAndLastName", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.successfulEnterFirstAndLastName()
		}

		override fun toString(): String = buildString("successfulEnterFirstAndLastName",)
	}

	private class EmptyLoginAndPasswordCommand<OMEGAVIEW : RegistrationView>(
		public val emptyField: String,
	) : ViewCommand<OMEGAVIEW>("emptyLoginAndPassword", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.emptyLoginAndPassword(emptyField)
		}

		override fun toString(): String = buildString("emptyLoginAndPassword","emptyField",emptyField)
	}

	private class CloseChoiceRoleCommand<OMEGAVIEW : RegistrationView>() :
			ViewCommand<OMEGAVIEW>("closeChoiceRole", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeChoiceRole()
		}

		override fun toString(): String = buildString("closeChoiceRole",)
	}

	private class LogCommand<OMEGAVIEW : RegistrationView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : RegistrationView>() :
			ViewCommand<OMEGAVIEW>("showLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : RegistrationView>() :
			ViewCommand<OMEGAVIEW>("closeLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> RegistrationPresenter):
		CustomPresenterFactory<RegistrationPresenter, RegistrationView> {
	RegistrationMvpViewState.Companion
	return CustomPresenterFactory<RegistrationPresenter, RegistrationView>(RegistrationPresenter::class
			as KClass<RegistrationPresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it) }
}
