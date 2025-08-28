package com.ethan.etodoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ethan.etodoapp.components.EditDialog
import com.ethan.etodoapp.components.TaskList
import com.ethan.etodoapp.ui.theme.ETodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {
    if (viewModel.isShowDialog) {
        EditDialog()
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { viewModel.isShowDialog = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "新規作成")
        }
    }) { innerPadding ->
        val tasks = viewModel.tasks.collectAsState(initial = emptyList())

        TaskList(
            modifier = Modifier.padding(innerPadding),
            tasks = tasks.value,
            onClickRow = {
                Log.e("onClickRow", it.toString())
                viewModel.setEditingTask(it)
                viewModel.isShowDialog = true
            },
            onClickDelete = {
                Log.e("onClickDelete", it.toString())

                viewModel.deleteTask(it)
            }
        )
    }
}
