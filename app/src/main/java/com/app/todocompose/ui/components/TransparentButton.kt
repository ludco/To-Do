package com.app.todocompose.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TransparentButton(@StringRes label: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
        ),
        elevation = ButtonDefaults.elevation(0.dp),
    ) {
        Text(stringResource(id = label).uppercase(), style = MaterialTheme.typography.button)
    }
}