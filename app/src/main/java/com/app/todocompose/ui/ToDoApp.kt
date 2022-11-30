package com.app.todocompose.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.components.dialogs.AddTaskDialog
import com.app.todocompose.ui.components.dialogs.BaseProjectDialog
import com.app.todocompose.ui.screens.HomeScreen
import com.app.todocompose.ui.viewmodels.ProjectViewModel
import com.app.todocompose.ui.viewmodels.TaskViewModel

@Composable
fun ToDoAppBarr(changeProjectMode: () -> Unit) {
    TopAppBar(title = { Text(stringResource(R.string.todo)) }, actions = {
        IconButton(onClick = changeProjectMode) {
            Icon(imageVector = Icons.Filled.List, contentDescription = "list")
        }
    })
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ToDoApp(taskViewModel: TaskViewModel, projectViewModel: ProjectViewModel) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var showModifyProjectDialog by remember { mutableStateOf(false) }
    var projectToEdit: Project? by remember { mutableStateOf(null) }
    var projectMode by remember { mutableStateOf(false) }

    val projectsList by projectViewModel.projects.collectAsState(initial = listOf())

    // Task operations
    fun addNewTask(taskName: String, projectId: Long) {
        val task = Task(name = taskName, projectId = projectId)
        taskViewModel.addTask(task)
        showDialog = false
    }

    fun deleteTask(taskId: Long) {
        taskViewModel.removeTask(taskId)
    }

    // Project operations
    fun addNewProject(projectName: String, projectColor: Color) {
        val project = Project(name = projectName, color = projectColor.toArgb())
        projectViewModel.addProject(project)
    }

    fun modifyProject(projectId: Long, projectName: String, projectColor: Color) {
        projectViewModel.modifyProject(projectId, projectName, projectColor.toArgb())
    }

    fun deleteProject(projectId: Long) {
        projectViewModel.deleteProject(projectId)
        showModifyProjectDialog = false
    }



    Scaffold(
        topBar = { ToDoAppBarr(changeProjectMode = { projectMode = !projectMode }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.testTag("test-FAB")
            ) {
                Icon(Icons.Filled.Add, "Add", tint = Color.White)
            }
        }) {

        // Screen
        HomeScreen(
            taskViewModel.taskUiState,
            projectsList,
            projectMode,
            onDeleteTask = { taskId -> deleteTask(taskId) },
            openModifyProjectDialog = { projectId ->
                projectToEdit = projectsList.first { it.id == projectId }
                showModifyProjectDialog = true
            }
        )

        // Create Task Dialog
        if (showDialog) {
            AddTaskDialog(
                onOKClick = { taskName, project -> addNewTask(taskName, project.id) },
                onCancelClick = { showDialog = false },
                onProjectCreated = { projectName, projectColor ->
                    addNewProject(
                        projectName,
                        projectColor
                    )
                },
                projectsList = projectsList
            )

        }

        // Edit project Dialog
        if (showModifyProjectDialog) {
            BaseProjectDialog(
                onOKClick = { projectName, projectColor, projectId ->
                    modifyProject(
                        projectId!!,
                        projectName,
                        projectColor,
                    )
                    showModifyProjectDialog = false
                },
                onCancelClick = { showModifyProjectDialog = false },
                editMode = true,
                projectToEdit = projectToEdit,
                onDeleteProject = { projectId -> deleteProject(projectId); }
            )

        }

        // Task Error Toast
        if (taskViewModel.errorMessage.isNotEmpty()) {
            Toast.makeText(
                context,
                taskViewModel.errorMessage,
                Toast.LENGTH_LONG
            ).show()
            taskViewModel.errorMessage = "An error occurred while getting projects"
        }

        // Project Error Toast
        if (projectViewModel.errorMessage.isNotEmpty()) {
            Toast.makeText(
                context,
                projectViewModel.errorMessage,
                Toast.LENGTH_LONG
            ).show()
            projectViewModel.errorMessage = ""
        }
    }
}

