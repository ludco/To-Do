package com.app.todocompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.todocompose.MainApplication
import com.app.todocompose.data.repository.ProjectRepository
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

class ProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    private lateinit var _projects: Flow<List<Project>>
    val projects: Flow<List<Project>>
        get() = _projects


    init {
        getProjects()
    }

    private fun getProjects() {
        viewModelScope.launch {
            try {
                val result = projectRepository.getProjects()
                _projects = result
            } catch (e: IOException) {
                //TODO
            }
        }
    }

    fun addProject(project: Project) {
        viewModelScope.launch {
            try {
                projectRepository.addProject(project)
            } catch (e: IOException) {
                //TODO
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val projectRepository = application.container.projectRepository
                ProjectViewModel(projectRepository = projectRepository)
            }
        }
    }

}