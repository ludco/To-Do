package com.app.todocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project

@Composable
fun OutlineDropDown(
    selectedProject: Project,
    projectList: List<Project>,
    onChooseItem: (Project) -> Unit,
    onCreateNewProject: () -> Unit,
    modifier: Modifier
) {

    var expanded by remember { mutableStateOf(false) }

    Row(Modifier.fillMaxWidth()) {
        Box() {
            OutlinedTextField(
                value = selectedProject.name,
                onValueChange = { },
                label = { Text(stringResource(R.string.project)) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_folder_24),
                        contentDescription = stringResource(R.string.edit)
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                enabled = false

            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                projectList.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        onChooseItem(item)
                        expanded = false
                    }) { Text(text = item.name, style = MaterialTheme.typography.caption) }
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable(onClick = onCreateNewProject),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "Create new project",
                        style = MaterialTheme.typography.caption,
                    )
                }

            }
        }
    }
}