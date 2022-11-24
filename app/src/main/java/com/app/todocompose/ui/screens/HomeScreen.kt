package com.app.todocompose.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.components.ProjectItem
import com.app.todocompose.ui.components.TaskItem
import com.app.todocompose.ui.viewmodels.TaskUiState

@Composable
fun HomeScreen(
    taskUiState: TaskUiState,
    projectList: List<Project>,
    projectMode: Boolean,
    onDeleteTask: (Long) -> Unit
) {
    when (taskUiState) {
        is TaskUiState.Success -> {
            val tasksList by taskUiState.tasks.collectAsState(initial = listOf())
            return TaskScreen(tasksList, projectList, projectMode, onDeleteTask)
        }
        is TaskUiState.Loading -> LoadingScreen()
        is TaskUiState.Error -> ErrorScreen()
    }
}

@Composable
fun TaskScreen(
    tasksList: List<Task>, projectList: List<Project>,
    projectMode: Boolean,
    onDeleteTask: (Long) -> Unit
) {
    if (tasksList.isEmpty()) {
        AllDoneScreen()
    } else {
        TasksListScreen(tasksList, projectList, onDeleteTask, projectMode)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier.testTag("amphibians-loader"))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(stringResource(R.string.whoops))
    }
}

@Composable
fun TasksListScreen(
    tasksList: List<Task>,
    projectList: List<Project>,
    onDeleteTask: (Long) -> Unit,
    projectMode: Boolean,
    modifier: Modifier = Modifier
) {
    if (projectMode) {
        LazyColumn(modifier = modifier) {
            items(projectList, key = { project -> project.id }) { project ->
                ProjectItem(project, tasksList, onDeleteTask)
            }
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(tasksList, key = { task -> task.id }) { task ->
                TaskItem(task, onDeleteTask)
            }
        }
    }
}

@Composable
fun AllDoneScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.check),
            Modifier.size(100.dp)
        )
        Text(stringResource(R.string.all_done), style = MaterialTheme.typography.body2)
    }
}