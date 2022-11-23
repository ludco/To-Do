package com.app.todocompose.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.app.todocompose.domain.task.Task

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task>
        get() = _tasks

    fun addTask(task: Task) {
        _tasks.add(task)
        Log.d("TEST", tasks.size.toString())
    }

    fun removeTask(task: Task) {
        _tasks.remove(task)
    }
}