package com.example.todo.taskPreview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun TasksScreen(viewModel: TasksScreenViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    LazyColumn{
        items(tasks){
            TaskItem(
                task = it,
                onCompleteChecked = {taskComplete -> viewModel.onTaskComplete(taskComplete)},
                onFavoriteClicked = {taskFavorite -> viewModel.onTaskFavorite(taskFavorite)  },
                onDeleteClicked = {taskDelete -> viewModel.onTaskDelete(taskDelete)}){
                // navigate to task view screen
            }
        }
    }
}