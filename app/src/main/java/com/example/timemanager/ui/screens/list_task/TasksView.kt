package com.example.timemanager.ui.screens.list_task

import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BaseView
import com.omegar.mvp.viewstate.strategy.MoxyViewCommand
import com.omegar.mvp.viewstate.strategy.StrategyType

interface TasksView : BaseView {

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setTaskList(list: List<Task>)

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setIconSortField(state: TasksActivity.StateSortField)

    @MoxyViewCommand(StrategyType.ADD_TO_END)
    fun setIconSortOrder(state: TasksActivity.StateOrder)
}