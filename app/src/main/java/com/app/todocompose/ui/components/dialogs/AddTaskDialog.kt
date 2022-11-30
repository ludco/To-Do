package com.app.todocompose.ui.components.dialogs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project
import com.app.todocompose.ui.components.OutlineDropDown
import com.app.todocompose.ui.components.TransparentButton

@Composable
fun AddTaskDialog(
    onOKClick: (String, Project) -> Unit,
    onCancelClick: () -> Unit,
    onProjectCreated: (String, Color) -> Unit,
    projectsList: List<Project>,
    modifier: Modifier = Modifier,

    ) {
    var showProjectDialog by remember { mutableStateOf(false) }
    var taskName by remember { mutableStateOf("") }
    var selectedProject by remember { mutableStateOf(Project(id = 0, name = "", color = 0)) }
    val context = LocalContext.current


    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Text(stringResource(R.string.add_a_task), modifier.padding(bottom = 8.dp))
                OutlineDropDown(
                    selectedProject = selectedProject,
                    projectList = projectsList,
                    onChooseItem = { item -> selectedProject = item },
                    onCreateNewProject = { showProjectDialog = true },
                    modifier = Modifier
                )
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { value -> taskName = value },
                    label = { Text(stringResource(R.string.task_name)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    ),
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
                                if (taskName.isNotEmpty()) {
                                    onOKClick(taskName, selectedProject)
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter a task name !",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
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
    if (showProjectDialog) {
        BaseProjectDialog(
            onOKClick = { projectName, projectColor, _ ->
                onProjectCreated(projectName, projectColor)
                showProjectDialog = false
            },
            onCancelClick = { showProjectDialog = false },
            editMode = false
        )
    }
}

