package com.example.timemanager.ui.screens.profile

import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface ProfileView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setProfile(profile: Profile)

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setDefaultVisibleButton()

    @MoxyViewCommand(StrategyType.SINGLE)
    fun gotoAuthorization()
}