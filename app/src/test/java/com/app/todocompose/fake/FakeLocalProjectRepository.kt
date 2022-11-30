package com.app.todocompose.fake

import com.app.todocompose.data.repository.ProjectRepository
import com.app.todocompose.domain.project.Project
import kotlinx.coroutines.flow.Flow

class FakeLocalProjectRepository : ProjectRepository{
    override suspend fun getProjects(): Flow<List<Project>> {
        return FakeDataSource.projectList
    }

    override suspend fun addProject(project: Project) {
        TODO("Not yet implemented")
    }

    override suspend fun editProject(projectId: Long, projectName: String, projectColor: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProject(projectId: Long) {
        TODO("Not yet implemented")
    }
}