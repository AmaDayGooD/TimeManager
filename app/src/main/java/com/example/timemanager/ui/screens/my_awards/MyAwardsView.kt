package com.example.timemanager.ui.screens.my_awards

import com.example.timemanager.entity.Award
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
interface MyAwardsView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setListAdapter()

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setList(list: List<Award>)
}