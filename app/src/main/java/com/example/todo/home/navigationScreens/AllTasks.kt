package com.example.todo.home.navigationScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo.R
import com.example.todo.taskPreview.NoTasks
import com.example.todo.taskPreview.TaskItem
import com.example.todo.taskPreview.TasksScreenViewModel


@Composable
fun AllTasksScreen(navController: NavHostController, viewModel: TasksScreenViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    val change by viewModel.isChange.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    if (tasks.isNotEmpty()){
        if (change || change.not()) {

            Column {

                LazyColumn {
                    items(tasks.filter {task -> !task.isComplete }) {
                        TaskItem(
                            task = it,
                            showDialog=showDialog,
                            onCompleteChecked = { taskComplete -> viewModel.onTaskComplete(taskComplete) },
                            onFavoriteClicked = { taskFavorite -> viewModel.onTaskFavorite(taskFavorite) },
                            onDeleteConfirm = { viewModel.onTaskDelete() },
                            onShowDialog = { task -> viewModel.onShowDialogDelete(task) },
                            onDialogDismiss = {  viewModel.onDismissDialogDelete() },
                            onDateChange = {task,year,month,day -> viewModel.onDateChange(task,year,month,day)},
                            onTimeChange = {task,hour,minute -> viewModel.onTimeChange(task,hour,minute)},
                        ) {
                            // navigate to task view screen
                        }
                    }
                    item {
                        if (tasks.filter {task -> task.isComplete}.isNotEmpty() && tasks.filter {task -> !task.isComplete}.isNotEmpty()){
                            Divider(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    items(tasks.filter {task -> task.isComplete }) {
                        TaskItem(
                            task = it,
                            showDialog=showDialog,
                            onCompleteChecked = { taskComplete -> viewModel.onTaskComplete(taskComplete) },
                            onFavoriteClicked = { taskFavorite -> viewModel.onTaskFavorite(taskFavorite) },
                            onDeleteConfirm = {viewModel.onTaskDelete() },
                            onShowDialog = { task -> viewModel.onShowDialogDelete(task) },
                            onDialogDismiss = { viewModel.onDismissDialogDelete() },
                            onDateChange = {task,year,month,day -> viewModel.onDateChange(task,year,month,day)},
                            onTimeChange = {task,hour,minute -> viewModel.onTimeChange(task,hour,minute)},
                        ) {
                            // navigate to task view screen
                        }
                    }

                }


            }
        }
    }else{
        NoTasks(
            R.drawable.alltasks,
            " no to day tasks",
            "No Tasks Found here",
            "Here is where you can find all tasks that are scheduled."
        )
    }



}


