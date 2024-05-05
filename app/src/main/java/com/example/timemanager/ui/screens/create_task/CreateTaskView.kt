package com.example.timemanager.ui.screens.create_task

import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType


/**
 * Created by Alexander Shibaev on 04.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
interface CreateTaskView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setNameChild(nameChild: List<String>)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun showResultCreateTask(result: Boolean)
}