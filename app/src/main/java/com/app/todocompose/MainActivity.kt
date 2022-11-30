package com.app.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.todocompose.ui.ToDoApp
import com.app.todocompose.ui.theme.ToDoComposeTheme
import com.app.todocompose.ui.viewmodels.ProjectViewModel
import com.app.todocompose.ui.viewmodels.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                val taskViewModel: TaskViewModel =
                    viewModel(factory = TaskViewModel.Factory)
                val projectViewModel: ProjectViewModel =
                    viewModel(factory = ProjectViewModel.Factory)

                ToDoApp(taskViewModel, projectViewModel)
            }
        }
    }
}

