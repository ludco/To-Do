package com.app.todocompose.data

import com.app.todocompose.MainApplication
import com.app.todocompose.data.repository.LocalProjectRepository
import com.app.todocompose.data.repository.LocalTaskRepository
import com.app.todocompose.data.repository.ProjectRepository
import com.app.todocompose.data.repository.TaskRepository

interface AppContainer {
    val taskRepository: TaskRepository
    val projectRepository: ProjectRepository
}

class DefaultAppContainer : AppContainer {
    private val application = MainApplication.instance

    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(application)
    }
    override val taskRepository: TaskRepository by lazy { LocalTaskRepository(db.getTaskDao()) }
    override val projectRepository: ProjectRepository by lazy { LocalProjectRepository(db.getProjectDao()) }
}