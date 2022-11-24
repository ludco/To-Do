package com.app.todocompose.fake


import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.*

object FakeDataSource {

    var tasksList =
        flow { emit(listOf(Task(id = 0, name = "Task One"), Task(id = 1, name = "Task Two"))) }

}