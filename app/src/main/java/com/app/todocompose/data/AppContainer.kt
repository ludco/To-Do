package com.app.todocompose.data

import androidx.room.Room
import com.app.todocompose.MainApplication
import com.app.todocompose.data.repository.LocalTaskRepository
import com.app.todocompose.data.repository.TaskRepository

interface AppContainer {
    val taskRepository: TaskRepository
}

class DefaultAppContainer : AppContainer {
    private val application = MainApplication.instance

    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(application)
    }
    override val taskRepository: TaskRepository by lazy { LocalTaskRepository(db.getTaskDao()) }
}