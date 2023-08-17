package com.example.todo.commonComponents


sealed class Screens(val route:String){
    // Single Screens
    object LoginScreen:Screens(route = "login")
    object SignUpScreen:Screens(route = "signUp")




    // Route for Nested Navigation
    object AuthRoute:Screens(route = "auth")
    object HomeRoute:Screens(route = "navigationHome")

    object AppNavigation:Screens(route = "appNavigation")

}