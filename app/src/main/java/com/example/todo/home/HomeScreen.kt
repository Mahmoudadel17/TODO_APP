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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.todo.commonComponents.BottomNav
import com.example.todo.home.navigationScreens.NavigationScreens
import com.example.todo.taskPreview.TasksScreenViewModel
import com.example.todo.ui.theme.floatingActionButton

@Composable
fun HomeScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomNavController = rememberNavController()
    val items = listOf(
        NavigationScreens.ToDay,
        NavigationScreens.AllTasks,
        NavigationScreens.Favorite,
        NavigationScreens.Settings)
    Scaffold(
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
            FloatingActionButton(containerColor = floatingActionButton,onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add,tint = MaterialTheme.colorScheme.background, contentDescription = "Add")
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)){
            BottomNav(navController, bottomNavController)
        }
    }
}