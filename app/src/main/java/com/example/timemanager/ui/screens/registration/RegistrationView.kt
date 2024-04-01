package com.example.timemanager.ui.screens.registration

import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface RegistrationView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun successfulRegistration()

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun failedRegistration(text: String)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun emptyFirstAndLastNames(emptyField: String)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun successfulEnterFirstAndLastName()

    @MoxyViewCommand(StrategyType.SINGLE)
    fun emptyLoginAndPassword(emptyField: String)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun closeChoiceRole()
}