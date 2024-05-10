package com.example.timemanager.ui.screens.awards

import com.example.timemanager.entity.Award
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
interface AwardsView: BaseView {

    @MoxyViewCommand(StrategyType.SINGLE)
    fun resultCreatingAward(result: Boolean)

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setAwardList(awardList: List<Award>)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun setListAdapter(userBalance: Int)
}