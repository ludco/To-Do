package com.app.todocompose.fake

import com.app.todocompose.data.repository.TaskRepository
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow

class FakeLocalTaskRepository : TaskRepository {
    override suspend fun getTasks(): Flow<List<Task>> {
        return FakeDataSource.tasklist
    }

    override suspend fun addTask(task: Task) {
        FakeDataSource.addTask(task)
    }

    override suspend fun deleteAssociatedTasks(projectId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: Long) {
        FakeDataSource.removeTask(taskId)
    }

}