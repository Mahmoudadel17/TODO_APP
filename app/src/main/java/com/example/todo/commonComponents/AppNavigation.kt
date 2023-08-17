package com.example.todo.commonComponents

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.todo.auth.LoginScreen
import com.example.todo.auth.SignUpScreen
import com.example.todo.home.HomeScreen
import com.example.todo.home.navigationScreens.AllTasksScreen
import com.example.todo.home.navigationScreens.FavoriteScreen
import com.example.todo.home.navigationScreens.NavigationScreens
import com.example.todo.home.navigationScreens.SettingsScreens
import com.example.todo.home.navigationScreens.ToDayTasksScreens
import com.example.todo.taskPreview.TasksScreenViewModel

@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.AuthRoute.route){
        authNav(navController)
        homeApp(navController)
    }
}



fun NavGraphBuilder.authNav(navController: NavController){
    navigation(startDestination = Screens.LoginScreen.route,route=Screens.AuthRoute.route){
        composable(route = Screens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screens.SignUpScreen.route){
            SignUpScreen(navController = navController)
        }

    }
}



fun NavGraphBuilder.homeApp(navController: NavHostController) {
    navigation(startDestination = Screens.AppNavigation.route,route= Screens.HomeRoute.route){

        composable(route = Screens.AppNavigation.route){
            HomeScreen(navController)
        }

    }
}




@Composable
fun BottomNav(appNavController: NavHostController, bottomNavController: NavHostController) {
    NavHost(navController = appNavController, startDestination = NavigationScreens.ToDay.route){

        composable(route = NavigationScreens.ToDay.route){
            ToDayTasksScreens(bottomNavController)
        }
        composable(route = NavigationScreens.AllTasks.route){
            AllTasksScreen(bottomNavController)
        }
        composable(route = NavigationScreens.Settings.route){
            SettingsScreens(bottomNavController)
        }
        composable(route = NavigationScreens.Favorite.route){
            FavoriteScreen(bottomNavController)
        }

    }
}
