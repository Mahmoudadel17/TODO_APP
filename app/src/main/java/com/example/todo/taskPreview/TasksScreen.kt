package com.example.todo.taskPreview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TasksScreen(viewModel: TasksScreenViewModel) {

    val tasks by viewModel.tasks.collectAsState()
    val change by viewModel.isChange.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    if (change || !change) {
        Column {

            LazyColumn {
                item{

                    Text(
                        text = viewModel.formatDate(),
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 5.dp)

                    )
                }
                items(tasks.filter { task ->
                    !task.isComplete
                }) {
                    TaskItem(
                        task = it,
                        showDialog = showDialog,
                        onCompleteChecked = { taskComplete -> viewModel.onTaskComplete(taskComplete) },
                        onFavoriteClicked = { taskFavorite -> viewModel.onTaskFavorite(taskFavorite) },
                        onDeleteConfirm = { taskDelete -> viewModel.onTaskDelete(taskDelete) },
                        onShowDialog = {viewModel.onShowDialogDelete()},
                        onDialogDismiss = {viewModel.onDismissDialogDelete() },
                        onDateChange = {task,year,month,day -> viewModel.onDateChange(task,year,month,day)},
                        onTimeChange = {task,hour,minute -> viewModel.onTimeChange(task,hour,minute)},
                        ) {
                        // navigate to task view screen
                    }
                }
                item {
                    if (tasks.any { task -> task.isComplete }){
                        Divider(
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                items(tasks.filter { task ->
                    task.isComplete
                }) {
                    TaskItem(
                        task = it,
                        showDialog = showDialog,
                        onCompleteChecked = { taskComplete -> viewModel.onTaskComplete(taskComplete) },
                        onFavoriteClicked = { taskFavorite -> viewModel.onTaskFavorite(taskFavorite) },
                        onDeleteConfirm = { taskDelete -> viewModel.onTaskDelete(taskDelete) },
                        onShowDialog = {viewModel.onShowDialogDelete()},
                        onDialogDismiss = {viewModel.onDismissDialogDelete()},
                        onDateChange = {task,year,month,day -> viewModel.onDateChange(task,year,month,day)},
                        onTimeChange = {task,hour,minute -> viewModel.onTimeChange(task,hour,minute)},
                        ) {
                        // navigate to task view screen
                    }
                }

            }


        }
    }
}