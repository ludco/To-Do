package com.app.todocompose.fake


import androidx.compose.ui.graphics.toArgb
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.theme.Teal200
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow

object FakeDataSource {
    private val _tasklist = MutableSharedFlow<List<Task>>() // private mutable shared flow
    val tasklist = _tasklist.asSharedFlow()
    var projectList =
        flow { emit(listOf(Project(id = 1, name = "Project Test", color = Teal200.toArgb()))) }


    suspend fun addTask(task: Task) {
        _tasklist.emit(listOf(task))
    }

    suspend fun removeTask(taskId: Long) {
        _tasklist.emit(listOf())
    }
}