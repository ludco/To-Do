package com.app.todocompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.todocompose.MainApplication
import com.app.todocompose.data.AppDatabase
import com.app.todocompose.data.repository.LocalTaskRepository
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException


class TaskViewModel() : ViewModel() {
    private val application = MainApplication.instance
    private lateinit var _tasks: Flow<List<Task>>
    val tasks: Flow<List<Task>>
        get() = _tasks

    private val taskRepository: LocalTaskRepository

    init {
        val db = AppDatabase.getInstance(application)
        val taskDao = db.getTaskDao()
        taskRepository = LocalTaskRepository(taskDao)

        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val result = taskRepository.getTasks()
                _tasks = result
            } catch (e: IOException) {
                //TODO
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.addTask(task)
            } catch (e: IOException) {
                //TODO
            }
        }
    }

    fun removeTask(taskId: Long) {
        viewModelScope.launch {
            try {
                taskRepository.deleteTask(taskId)
            } catch (e: IOException) {
                //TODO
            }
        }
    }
}
