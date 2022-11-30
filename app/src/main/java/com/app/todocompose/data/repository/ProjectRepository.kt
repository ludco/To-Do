package com.app.todocompose.data.repository

import com.app.todocompose.data.dao.ProjectDao
import com.app.todocompose.domain.project.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun getProjects(): Flow<List<Project>>

    suspend fun addProject(project: Project)

    suspend fun editProject(projectId: Long, projectName: String, projectColor: Int)

    suspend fun deleteProject(projectId: Long)
}

class LocalProjectRepository(private val projectDao: ProjectDao) : ProjectRepository {
    override suspend fun getProjects(): Flow<List<Project>> {
        return projectDao.getProjects()
    }

    override suspend fun addProject(project: Project) {
        projectDao.insert(project)
    }

    override suspend fun editProject(projectId: Long, projectName: String, projectColor: Int) {
        projectDao.update(projectId, projectName, projectColor)
    }

    override suspend fun deleteProject(projectId: Long) {
        projectDao.delete(projectId)
    }
}