package com.app.todocompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.components.AddTaskDialog
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
    var showDialog by remember { mutableStateOf(false) }
    var projectMode by remember { mutableStateOf(false) }

    val tasksList by taskViewModel.tasks.collectAsState(initial = listOf())
    val projectsList by projectViewModel.projects.collectAsState(initial = listOf())

    fun addNewTask(taskName: String, projectId: Long) {
        val task = Task(name = taskName, projectId = projectId)
        taskViewModel.addTask(task)
        showDialog = false
    }

    fun projectNewTask(projectName: String) {
        val project = Project(name = projectName)
        projectViewModel.addProject(project)
    }

    fun deleteTask(taskId: Long) {
        taskViewModel.removeTask(taskId)
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
        if (showDialog) {
            AddTaskDialog(
                onOKClick = { taskName, project -> addNewTask(taskName, project.id) },
                onCancelClick = { showDialog = false },
                onProjectCreated = { projectName -> projectNewTask(projectName) },
                projectsList = projectsList
            )

        }
        HomeScreen(
            tasksList,
            projectsList,
            projectMode,
            onDeleteTask = { taskId -> deleteTask(taskId) })
    }
}

