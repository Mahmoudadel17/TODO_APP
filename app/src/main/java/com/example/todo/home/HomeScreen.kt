package com.example.todo.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.todo.commonComponents.BottomNav
import com.example.todo.home.navigationScreens.NavigationScreens
import com.example.todo.taskPreview.TasksScreenViewModel
import com.example.todo.ui.theme.floatingActionButton



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel:TasksScreenViewModel = viewModel()
    
    

    val navController = rememberNavController()
    val items = listOf(
        NavigationScreens.ToDay,
        NavigationScreens.AllTasks,
        NavigationScreens.Favorite,
        NavigationScreens.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = { 
                    var title = NavigationScreens.ToDay.title
                    items.forEach {screen ->
                        if (currentRoute == screen.route){
                            title = screen.title
                        }
                    }
                    Text(
                        text = title,
                        fontSize = 35.sp,
                        color = floatingActionButton,
                    ) },
            )
        },
        
        
        bottomBar = {
            BottomNavigation (elevation = 10.dp,backgroundColor = MaterialTheme.colorScheme.primary){
               
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        label = { if(currentRoute == screen.route) Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selectedContentColor = floatingActionButton,
                        unselectedContentColor = Color.Gray,
                    )
                }

            }
        },
        floatingActionButton = {
            if (currentRoute !=  NavigationScreens.Settings.route){
                FloatingActionButton(containerColor = floatingActionButton,onClick = {

                }) {
                    Icon(imageVector = Icons.Filled.Add,tint = MaterialTheme.colorScheme.background, contentDescription = "Add")
                }
            }

        }

    ) {
        Box(modifier = Modifier.padding(it)){
            BottomNav(navController,navHostController,viewModel)

        }

    }
}