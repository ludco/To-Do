package com.app.todocompose.fake


import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.*

object FakeDataSource {

    var tasksList =
        flow {
            emit(
                listOf(
                    Task(id = 0, name = "Task One", projectId = 1),
                    Task(id = 1, name = "Task Two", projectId = 1)
                )
            )
        }

    var projectList =
        flow { emit(listOf(Project(id = 1, name = "Project"))) }
}