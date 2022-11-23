package com.app.todocompose.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.app.todocompose.ui.components.AddTaskDialog
import com.app.todocompose.ui.screens.HomeScreen
import com.app.todocompose.ui.viewmodels.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.todocompose.domain.task.Task

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
fun ToDoApp(taskViewModel: TaskViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }

    fun addNewTask(taskName: String) {
        Log.d("TEST", "ici")
        val task = Task(name = taskName)
        taskViewModel.addTask(task)
        showDialog = false
    }

    fun deleteTask(task: Task) {
        taskViewModel.removeTask(task)
    }

    Scaffold(topBar = { ToDoAppBarr() }, floatingActionButton = {
        FloatingActionButton(onClick = { showDialog = true }) {
            Icon(Icons.Filled.Add, "Add", tint = Color.White)
        }
    }) {
        if (showDialog) {
            AddTaskDialog(
                onOKClick = { taskName -> addNewTask(taskName) },
                onCancelClick = { showDialog = false })
        }
        HomeScreen(taskViewModel.tasks, onDeleteTask = { task -> deleteTask(task) })
    }
}

