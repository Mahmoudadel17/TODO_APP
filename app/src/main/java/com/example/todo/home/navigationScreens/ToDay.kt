package com.example.todo.home.navigationScreens

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todo.taskPreview.TasksScreen
import com.example.todo.taskPreview.TasksScreenViewModel


@Composable
fun ToDayTasksScreens(bottomNavController: NavHostController) {
    val viewModel: TasksScreenViewModel = viewModel()
    TasksScreen(viewModel)
}