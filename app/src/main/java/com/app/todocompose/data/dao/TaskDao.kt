package com.app.todocompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
     fun getTasks(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Query("DELETE FROM task WHERE id=:taskId")
    suspend fun delete(taskId: Long): Int
}