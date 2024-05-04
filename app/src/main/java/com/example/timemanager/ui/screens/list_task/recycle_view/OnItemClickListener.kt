package com.example.timemanager.ui.screens.list_task.recycle_view

import android.widget.Button
import com.example.timemanager.data.Condition

interface OnItemClickListener {
    fun onClickOpenTask(taskId: Int)

    fun setState(button: Button, state: Condition): Button

    fun getResourcesString(idString: Int, vararg formatArgs: Any): String
}