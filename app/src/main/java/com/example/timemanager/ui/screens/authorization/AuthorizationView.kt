package com.example.timemanager.ui.screens.authorization

import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface AuthorizationView : BaseView {

    @MoxyViewCommand(StrategyType.SINGLE)
    fun requestGotoMainActivity()

    @MoxyViewCommand(StrategyType.SINGLE)
    fun requestGotoRegisterActivity()

    @MoxyViewCommand(StrategyType.SINGLE)
    fun showToast(text: String)
@MoxyViewCommand(StrategyType.SINGLE)
    fun invalidPassword()
}