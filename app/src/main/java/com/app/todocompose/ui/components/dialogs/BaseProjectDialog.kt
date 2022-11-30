package com.app.todocompose.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R
import com.app.todocompose.domain.project.Project
import com.app.todocompose.ui.components.TransparentButton
import com.app.todocompose.ui.utils.ProjectColors

@Composable
fun BaseProjectDialog(
    onOKClick: (String, Color, Long?) -> Unit,
    onCancelClick: () -> Unit,
    editMode: Boolean,
    modifier: Modifier = Modifier,
    projectToEdit: Project? = null,
    onDeleteProject: (Long) -> Unit = {},

    ) {
    var projectName by remember { mutableStateOf(if (editMode) projectToEdit!!.name else "") }
    var projectColor by remember { mutableStateOf(if (editMode) Color(projectToEdit!!.color) else Color.Gray) }

    var showWarningDialog by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                //Content
                if (editMode) {
                    ModifyProjectDialogContent(
                        projectName,
                        onProjectNameChange = { value -> projectName = value },
                        projectColor,
                    )
                } else {
                    AddProjectDialogContent(
                        projectName,
                        onProjectNameChange = { value -> projectName = value },
                        projectColor
                    )
                }

                // Choose Project Color
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    ProjectColors.colors.forEach {
                        Button(
                            onClick = { projectColor = it },
                            colors = ButtonDefaults.buttonColors(backgroundColor = it),
                            modifier = Modifier
                                .padding(4.dp)
                                .size(20.dp)
                        ) {}
                    }
                }
                // Dialog Button
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    if (editMode) {
                        IconButton(
                            onClick = { showWarningDialog = true },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = stringResource(R.string.delete),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                    TransparentButton(
                        label = R.string.button_cancel,
                        onClick = onCancelClick
                    )
                    TransparentButton(
                        label = R.string.button_ok,
                        onClick = {
                            onOKClick(
                                projectName,
                                projectColor,
                                projectToEdit?.id
                            )
                        })
                }
            }
        }
    }
    if (showWarningDialog) {
        WarningDialog(
            onOKClick = { onDeleteProject(projectToEdit!!.id); showWarningDialog = false },
            onCancelClick = { showWarningDialog = false })
    }
}