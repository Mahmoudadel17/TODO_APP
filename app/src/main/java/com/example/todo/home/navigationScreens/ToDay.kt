package com.example.todo.home.navigationScreens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.todo.taskPreview.TasksScreen
import com.example.todo.taskPreview.TasksScreenViewModel


@Composable
fun ToDayTasksScreen(navController: NavHostController, viewModel: TasksScreenViewModel) {
    TasksScreen(viewModel)
}