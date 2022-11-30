package com.app.todocompose.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.todocompose.R
import com.app.todocompose.ui.components.TransparentButton

@Composable
fun WarningDialog(
    onOKClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onCancelClick) {
        Box(modifier.background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                Text(stringResource(R.string.warning_message))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TransparentButton(
                        label = R.string.button_cancel,
                        onClick = onCancelClick
                    )
                    TransparentButton(
                        label = R.string.button_ok,
                        onClick = onOKClick
                    )
                }
            }
        }
    }
}