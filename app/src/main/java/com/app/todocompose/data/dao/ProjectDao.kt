package com.app.todocompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.todocompose.domain.project.Project
import kotlinx.coroutines.flow.Flow


@Dao
interface ProjectDao {
    @Query("SELECT * FROM project")
    fun getProjects(): Flow<List<Project>>

    @Insert
    suspend fun insert(project: Project)

    @Query("UPDATE project SET name =:projectName, color=:projectColor WHERE id=:projectId")
    suspend fun update(projectId: Long, projectName: String, projectColor: Int): Int

    @Query("DELETE FROM project WHERE id=:projectId")
    suspend fun delete(projectId: Long): Int

}
