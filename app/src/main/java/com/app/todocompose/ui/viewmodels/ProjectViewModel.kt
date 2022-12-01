package com.app.todocompose.ui.viewmodels

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.todocompose.MainApplication
import com.app.todocompose.data.repository.ProjectRepository
import com.app.todocompose.domain.project.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

const val PROJECT = "PROJECT"

class ProjectViewModel(
    private val projectRepository: ProjectRepository,
) : ViewModel() {
    private lateinit var _projects: Flow<List<Project>>
    val projects: Flow<List<Project>>
        get() = _projects

    var errorMessage by mutableStateOf("")


    init {
        getProjects()
    }

    private fun getProjects() {
        viewModelScope.launch {
            try {
                val result = projectRepository.getProjects()
                _projects = result
            } catch (e: SQLiteException) {
                Log.e(PROJECT, e.toString())
                errorMessage = "An error occurred while getting projects"
            }
        }
    }

    fun addProject(project: Project) {
        viewModelScope.launch {
            try {
                projectRepository.addProject(project)
            } catch (e: SQLiteException) {
                Log.e(PROJECT, e.toString())
                errorMessage = "An error occurred while adding project"
            }
        }
    }

    fun modifyProject(projectId: Long, projectName: String, projectColor: Int) {
        viewModelScope.launch {
            try {
                projectRepository.editProject(projectId, projectName, projectColor)
            } catch (e: SQLiteException) {
                Log.e(PROJECT, e.toString())
                errorMessage = "An error occurred while editing project"
            }
        }
    }

    fun deleteProject(projectId: Long) {
        viewModelScope.launch {
            try {
                projectRepository.deleteProject(projectId)
            } catch (e: SQLiteException) {
                Log.e(PROJECT, e.toString())
                errorMessage = "An error occurred while deleting project"
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val projectRepository = application.container.projectRepository
                ProjectViewModel(
                    projectRepository = projectRepository,
                )
            }
        }
    }

}