package com.app.todocompose.data.repository

import com.app.todocompose.data.dao.TaskDao
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getTasks(): Flow<List<Task>>

    suspend fun addTask(task: Task)

    suspend fun deleteTask(taskId: Long)

    suspend fun deleteAssociatedTasks(projectId: Long)
}

class LocalTaskRepository(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun getTasks(): Flow<List<Task>> {
        return taskDao.getTasks()
    }

    override suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDao.delete(taskId)
    }

    override suspend fun deleteAssociatedTasks(projectId: Long) {
        taskDao.deleteAssociatedTasks(projectId)
    }
}