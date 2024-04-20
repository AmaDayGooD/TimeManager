package com.example.timemanager.ui.screens.profile

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
import kotlin.reflect.KClass

public open class ProfileMvpViewState<OMEGAVIEW : ProfileView> : OmegaMvpViewState<OMEGAVIEW>(),
		ProfileView {
	override fun showToast(text: String) {
		apply(ShowToastCommand(text))
	}

	override fun setProfile(profile: Profile) {
		apply(SetProfileCommand(profile))
	}

	override fun setDefaultVisibleButton() {
		apply(SetDefaultVisibleButtonCommand())
	}

	override fun gotoAuthorization() {
		apply(GotoAuthorizationCommand())
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
			MoxyReflector[ProfilePresenter::class] = {ProfileMvpViewState<ProfileView>() }}
	}

	private class ShowToastCommand<OMEGAVIEW : ProfileView>(
		public val text: String,
	) : ViewCommand<OMEGAVIEW>("showToast", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showToast(text)
		}

		override fun toString(): String = buildString("showToast","text",text)
	}

	private class SetProfileCommand<OMEGAVIEW : ProfileView>(
		public val profile: Profile,
	) : ViewCommand<OMEGAVIEW>("setProfile", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.setProfile(profile)
		}

		override fun toString(): String = buildString("setProfile","profile",profile)
	}

	private class SetDefaultVisibleButtonCommand<OMEGAVIEW : ProfileView>() :
			ViewCommand<OMEGAVIEW>("setDefaultVisibleButton", AddToEndStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.setDefaultVisibleButton()
		}

		override fun toString(): String = buildString("setDefaultVisibleButton",)
	}

	private class GotoAuthorizationCommand<OMEGAVIEW : ProfileView>() :
			ViewCommand<OMEGAVIEW>("gotoAuthorization", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.gotoAuthorization()
		}

		override fun toString(): String = buildString("gotoAuthorization",)
	}

	private class LogCommand<OMEGAVIEW : ProfileView>(
		public val message: String,
	) : ViewCommand<OMEGAVIEW>("log", SkipStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.log(message)
		}

		override fun toString(): String = buildString("log","message",message)
	}

	private class ShowLoadingCommand<OMEGAVIEW : ProfileView>() : ViewCommand<OMEGAVIEW>("showLoading",
			SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.showLoading()
		}

		override fun toString(): String = buildString("showLoading",)
	}

	private class CloseLoadingCommand<OMEGAVIEW : ProfileView>() :
			ViewCommand<OMEGAVIEW>("closeLoading", SingleStateStrategy) {
		override fun apply(mvpView: OMEGAVIEW) {
			mvpView.closeLoading()
		}

		override fun toString(): String = buildString("closeLoading",)
	}
}

public fun MvpDelegateHolder<*>.providePresenter(factoryBlock: () -> ProfilePresenter):
		CustomPresenterFactory<ProfilePresenter, ProfileView> {
	ProfileMvpViewState.Companion
	return CustomPresenterFactory<ProfilePresenter, ProfileView>(ProfilePresenter::class as
			KClass<ProfilePresenter>, factoryBlock).also { mvpDelegate.addCustomPresenterFields(it) }
}
