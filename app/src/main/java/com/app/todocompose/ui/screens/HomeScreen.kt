package com.app.todocompose.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.todocompose.R
import com.app.todocompose.domain.task.Task
import com.app.todocompose.ui.components.TaskItem

@Composable
fun HomeScreen(tasksList: List<Task>, onDeleteTask: (Task) -> Unit) {
    if (tasksList.size == 0) {
        AllDoneScreen()
    } else {
        TasksListScreen(tasksList, onDeleteTask)
    }
}

@Composable
fun TasksListScreen(
    tasksList: List<Task>,
    onDeleteTask: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(tasksList) { task ->
            TaskItem(task, onDeleteTask)
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