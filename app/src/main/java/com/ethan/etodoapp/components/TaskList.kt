package com.ethan.etodoapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ethan.etodoapp.data.Task

@Composable
fun TaskList(
    modifier: Modifier,
    tasks: List<Task>,
    onClickRow: (Task) -> Unit,
    onClickDelete: (Task) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(tasks) { task ->
            TaskRow(
                task = task,
                onClickRow = onClickRow,
                onClickDelete = onClickDelete
            )
        }
    }
}