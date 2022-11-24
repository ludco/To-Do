package com.app.todocompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.todocompose.MainApplication
import com.app.todocompose.data.repository.TaskRepository
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException



class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private lateinit var _tasks: Flow<List<Task>>
    val tasks: Flow<List<Task>>
        get() = _tasks


    init {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val taskRepository = application.container.taskRepository
                TaskViewModel(taskRepository = taskRepository)
            }
        }
    }
}
