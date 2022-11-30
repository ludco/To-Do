package com.app.todocompose.ui.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.todocompose.R

@Composable
fun AddProjectDialogContent(
    projectName: String,
    onProjectNameChange: (String) -> Unit,
    projectColor: Color,
    modifier: Modifier = Modifier
) {
    Text(stringResource(R.string.create_project), modifier.padding(bottom = 8.dp))
    OutlinedTextField(
        value = projectName,
        onValueChange = onProjectNameChange,
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

}