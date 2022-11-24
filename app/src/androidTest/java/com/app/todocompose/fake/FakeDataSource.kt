package com.app.todocompose.fake


import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object FakeDataSource {
    private val _tasklist = MutableSharedFlow<List<Task>>() // private mutable shared flow
    val tasklist = _tasklist.asSharedFlow()


    suspend fun addTask(task: Task) {
        _tasklist.emit(listOf(task))
    }

    suspend fun removeTask(taskId: Long) {
        _tasklist.emit(listOf())
    }
}