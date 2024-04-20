package com.example.timemanager.ui.screens.my_children

import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface MyChildrenView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setList(childList: List<Profile>)

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun successfullyAdded()

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun filedAdded()
}