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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.components.AddTaskDialog
import com.app.todocompose.ui.screens.HomeScreen
import com.app.todocompose.ui.viewmodels.ProjectViewModel
import com.app.todocompose.ui.viewmodels.TaskViewModel

@Composable
fun ToDoAppBarr() {
    TopAppBar(title = { Text("ToDo") }, actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.List, contentDescription = "list")
        }
    })
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ToDoApp(taskViewModel: TaskViewModel, projectViewModel: ProjectViewModel) {
    var showDialog by remember { mutableStateOf(false) }

    val tasksList by taskViewModel.tasks.collectAsState(initial = listOf())
    Log.d("TEST", tasksList.toString())
    val projectsList by projectViewModel.projects.collectAsState(initial = listOf())
    Log.d("TEST", projectsList.toString())


    fun addNewTask(taskName: String) {
        val task = Task(name = taskName)
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

    Scaffold(topBar = { ToDoAppBarr() }, floatingActionButton = {
        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier.testTag("test-FAB")
        ) {
            Icon(Icons.Filled.Add, "Add", tint = Color.White)
        }
    }) {
        if (showDialog) {
            AddTaskDialog(
                onOKClick = { taskName -> addNewTask(taskName) },
                onCancelClick = { showDialog = false },
                onProjectCreated = { projectName -> projectNewTask(projectName) },
                projectsList = projectsList
            )

        }
        HomeScreen(
            tasksList,
            onDeleteTask = { taskId -> deleteTask(taskId) })
    }
}

