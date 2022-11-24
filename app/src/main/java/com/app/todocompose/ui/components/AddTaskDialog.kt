package com.app.todocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R

@Composable
fun AddTaskDialog(
    onOKClick: (String) -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var taskName by remember { mutableStateOf("") }
    var selectedProject by remember { mutableStateOf("") }
    val items = listOf("Voiture", "Enfants", "Boulot")






    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Text(stringResource(R.string.add_a_task))
                OutlineDropDown(
                    selectedProject = selectedProject,
                    projectList = items,
                    onChooseItem = { item -> selectedProject = item },
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
                    TransparentButton(label = R.string.button_ok, onClick = { onOKClick(taskName) })
                }
            }
        }
    }
}