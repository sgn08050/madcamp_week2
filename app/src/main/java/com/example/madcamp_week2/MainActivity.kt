package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.madcamp_week2.ui.theme.MadCamp_week2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadCamp_week2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Login" ) {
                        composable("Login") {
                            LoginScreen(navController = navController)
                        }
                        composable("Home") {
                            HomeScreen(navController =  navController)
                        }
                        composable("CrewAdd") {
                            CrewAddScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
