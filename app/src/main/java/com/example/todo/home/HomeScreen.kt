package com.example.todo.home

import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.todo.commonComponents.BottomNav
import com.example.todo.home.navigationScreens.NavigationScreens
import com.example.todo.taskPreview.TasksScreenViewModel
import com.example.todo.ui.theme.floatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.todo.taskPreview.BottomSheetModel
import com.example.todo.taskPreview.BottomSheetViewModel
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel:TasksScreenViewModel = viewModel()
    val bottomSheetViewModel:BottomSheetViewModel = viewModel()

    var doubleBackToExitPressedOnce = false
    val showBottomSheet by bottomSheetViewModel.showBottomSheet.collectAsState()

    val activity = LocalOnBackPressedDispatcherOwner.current as ComponentActivity

    val context = LocalContext.current
    val navController = rememberNavController()
    val items = listOf(
        NavigationScreens.ToDay,
        NavigationScreens.AllTasks,
        NavigationScreens.Favorite,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val navigationBarItems = remember {NavigationBarItems.values()}
    var selectedIndex by remember { mutableStateOf(0) }



    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                selectedIndex = selectedIndex,
                modifier = Modifier.height(64.dp),
                cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = MaterialTheme.colorScheme.primary,
                ballColor = MaterialTheme.colorScheme.primary

            ) {
                navigationBarItems.forEach {item->
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            selectedIndex = item.ordinal
                            navController.navigate(items[item.ordinal].route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = item.icon,
                        contentDescription = "Bottom Bar Icon",
                        tint = if (selectedIndex == item.ordinal)floatingActionButton
                        else MaterialTheme.colorScheme.background
                    )

                }

                }

            }
        },

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

        floatingActionButton = {
            if (currentRoute !=  NavigationScreens.Settings.route){
                FloatingActionButton(containerColor = floatingActionButton,onClick = {
                    bottomSheetViewModel.onShowBottomSheet()
                }) {
                    Icon(imageVector = Icons.Filled.Add,tint = MaterialTheme.colorScheme.background, contentDescription = "Add")
                }
            }

        }

    ) {
        Box(modifier = Modifier.padding(it)){
            BottomNav(navController,navHostController,viewModel)
        }
        val title by bottomSheetViewModel.title.collectAsState()
        val content by bottomSheetViewModel.content.collectAsState()
        val isErrorTitle by bottomSheetViewModel.isErrorTitle.collectAsState()
        val isErrorContent by bottomSheetViewModel.isErrorContent.collectAsState()
        val titleErrorMessage by bottomSheetViewModel.titleErrorMessage.collectAsState()
        val contentErrorMessage by bottomSheetViewModel.contentErrorMessage.collectAsState()
        val dateTimeErrorMessage by bottomSheetViewModel.dateTimeErrorMessage.collectAsState()

        if (showBottomSheet){
            BottomSheetModel(
                title = title,
                isTitleError = isErrorTitle,
                titleMessageError = titleErrorMessage,
                content = content,
                isContentError = isErrorContent,
                contentMessageError = contentErrorMessage,
                dateTimeErrorMessage = dateTimeErrorMessage,
                onDismissRequest = {bottomSheetViewModel.onDismissRequest()},
                onTitleChange = {newTitle ->bottomSheetViewModel.onTitleChange(title = newTitle)},
                onContentChange = {newContent->bottomSheetViewModel.onContentChange(newContent)},
                onDateSelect = {year,month,day->bottomSheetViewModel.onDateSelect(year,month,day)},
                onTimeSelect = {hour,minute->bottomSheetViewModel.onTimeSelect(hour,minute)},
                onAddRequest = {bottomSheetViewModel.onFloatingActionButtonAdd(viewModel)}
            )
        }



        BackHandler(onBack = {
            // Handle the back button press here
            // Navigate to a specific screen using navController.navigate(...)

            if(currentRoute == items[0].route){
                if (doubleBackToExitPressedOnce) {
                    finishAffinity(activity) //This will exit the app
                } else {
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
                    Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
                    // Reset the flag after 2 seconds
                }
            }else{
                selectedIndex = 0
                navController.navigate(items[0].route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

        })


    }
}


enum class NavigationBarItems(val icon:ImageVector){
    ToDay(icon = Icons.Default.Home),
    AllTasks(icon = Icons.Default.List),
    Favorite(icon = Icons.Default.Favorite)
}

