package com.app.todocompose.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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


sealed interface TaskUiState {
    data class Success(val tasks: Flow<List<Task>>) : TaskUiState
    object Error : TaskUiState
    object Loading : TaskUiState
}

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    var taskUiState: TaskUiState by mutableStateOf(TaskUiState.Loading)
        private set


    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            try {
                val result = taskRepository.getTasks()
                taskUiState = TaskUiState.Success(result)
            } catch (e: IOException) {
                taskUiState = TaskUiState.Error
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.addTask(task)
            } catch (e: IOException) {
                taskUiState = TaskUiState.Error
            }
        }
    }

    fun removeTask(taskId: Long) {
        viewModelScope.launch {
            try {
                taskRepository.deleteTask(taskId)
            } catch (e: IOException) {
                taskUiState = TaskUiState.Error
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
