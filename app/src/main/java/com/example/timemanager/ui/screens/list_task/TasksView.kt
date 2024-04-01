package com.example.timemanager.ui.screens.list_task

import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface TasksView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setTaskList(list: List<Task>, state:Boolean)
}