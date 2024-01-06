package com.example.madcamp_week2

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object MainScreen : Screen("main_screen")
}


