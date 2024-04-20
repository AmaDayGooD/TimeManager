package com.example.timemanager.ui.base

import android.content.Context
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy
import com.omega_r.base.mvp.views.OmegaView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface BaseView : OmegaView {
    @MoxyViewCommand(StrategyType.SKIP)
    fun log(message: String)

    @MoxyViewCommand(StrategyType.SINGLE)
    fun showLoading()

    @MoxyViewCommand(StrategyType.SINGLE)
    fun closeLoading()

}