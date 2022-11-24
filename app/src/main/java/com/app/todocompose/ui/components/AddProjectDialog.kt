package com.app.todocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R
import com.app.todocompose.domain.project.ProjectColors

@Composable
fun AddProjectDialog(
    onOKClick: (String, Color) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var projectName by remember { mutableStateOf("") }
    var projectColor by remember { mutableStateOf(Color.Gray) }

    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Text(stringResource(R.string.create_project), modifier.padding(bottom = 8.dp))
                OutlinedTextField(
                    value = projectName,
                    onValueChange = { value -> projectName = value },
                    label = { Text(stringResource(R.string.project_name)) },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        leadingIconColor = projectColor,
                        backgroundColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = stringResource(R.string.edit)
                        )
                    }
                )
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TransparentButton(
                        label = R.string.button_cancel,
                        onClick = onCancelClick
                    )
                    TransparentButton(
                        label = R.string.button_ok,
                        onClick = { onOKClick(projectName, projectColor) })
                }
            }
        }
    }
}