package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.madcamp_week2.ViewModel.memberViewModel
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
                    val memberViewModel = memberViewModel()

                    NavHost(navController = navController, startDestination = "Login" ) {
                        composable("Login") {
                            LoginScreen(navController = navController, memberViewModel)
                        }
                        composable("Home") {
                            HomeScreen(navController =  navController)
                        }
                        composable("CrewNameAdd") {
                            CrewName(navController = navController)
                        }
                        composable("CrewDesAdd"){
                            CrewDes(navController = navController)
                        }
                        composable("CrewTagAdd") {
                            CrewTag(navController = navController)
                        }
                        composable("CrewTarAdd") {
                            CrewTar(navController = navController)
                        }
                        composable("CrewPeopleAdd") {
                            CrewPeople(navController = navController)
                        }
                        composable("SendAdd") {
                            CrewPeople(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
