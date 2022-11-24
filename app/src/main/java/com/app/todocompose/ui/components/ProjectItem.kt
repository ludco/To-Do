package com.app.todocompose.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.todocompose.domain.project.Project
import com.app.todocompose.domain.task.Task

@Composable
fun ProjectItem(
    project: Project,
    tasksList: List<Task>,
    onDeleteTask: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp, vertical = 8.dp
            ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color(project.color))
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = project.name, style = MaterialTheme.typography.body2)
        }

        tasksList.filter { task -> task.projectId == project.id }.let {
            it.forEach { task ->
                TaskItem(
                    task = task,
                    onDeleteTask = onDeleteTask
                )
            }
        }
    }
}