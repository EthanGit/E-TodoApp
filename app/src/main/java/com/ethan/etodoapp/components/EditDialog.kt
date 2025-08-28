package com.ethan.etodoapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ethan.etodoapp.MainViewModel

@Composable
fun EditDialog(viewModel: MainViewModel = hiltViewModel()) {
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetProperties()
        }
    }

    AlertDialog(
        onDismissRequest = {
            viewModel.isShowDialog = false
        },
        title = { Text(if (viewModel.isEditing) "タスク更新" else "タスク新規作成") },
        text = {
            Column {
                Text("タイトル")
                TextField(value = viewModel.title, onValueChange = {
                    viewModel.title = it
                })
                Text("詳細")
                TextField(value = viewModel.description, onValueChange = {
                    viewModel.description = it
                })
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier.width(120.dp),
                onClick = { viewModel.isShowDialog = false },
            ) {
                Text("キャンセル")
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    viewModel.isShowDialog = false
                    if (viewModel.isEditing) {
                        viewModel.updateTask()
                    } else {
                        viewModel.createTask()
                    }
                },
            ) {
                Text("OK")
            }
        },
    )
}