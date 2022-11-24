package com.app.todocompose.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project

@Composable
fun AddTaskDialog(
    onOKClick: (String, Project) -> Unit,
    onCancelClick: () -> Unit,
    onProjectCreated: (String) -> Unit,
    projectsList: List<Project>,
    modifier: Modifier = Modifier,

    ) {
    var showProjectDialaog by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }
    var selectedProject by remember { mutableStateOf(Project(id = 0, name = "")) }
    val context = LocalContext.current


    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Text(stringResource(R.string.add_a_task), modifier.padding(bottom = 8.dp))
                OutlineDropDown(
                    selectedProject = selectedProject,
                    projectList = projectsList,
                    onChooseItem = { item -> selectedProject = item },
                    onCreateNewProject = { showProjectDialaog = true },
                    modifier = Modifier
                )
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { value -> taskName = value },
                    label = { Text(stringResource(R.string.task_name)) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TransparentButton(
                        label = R.string.button_cancel,
                        onClick = onCancelClick
                    )
                    TransparentButton(
                        label = R.string.button_ok,
                        onClick = {
                            if (selectedProject.name.isNotEmpty()) {
                                onOKClick(taskName, selectedProject!!)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please choose a project !",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                }
            }
        }
    }
    if (showProjectDialaog) {
        AddProjectDialog(
            onOKClick = { projectname ->
                onProjectCreated(projectname);
                showProjectDialaog = false
            },
            onCancelClick = { showProjectDialaog = false })
    }
}

