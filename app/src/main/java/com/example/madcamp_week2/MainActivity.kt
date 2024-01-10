package com.example.madcamp_week2

import android.os.Bundle
import android.util.Log
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
                        composable("IDPWCreate") {
                            UserInform(navController = navController, memberViewModel)
                        }
                        composable("MoneyCreate") {
                            UserIncome(navController = navController, memberViewModel)
                        }

                        composable("Home") {
                            HomeScreen(navController =  navController, memberViewModel)
                        }
                        composable("EachCrewCard") {
                            EachCrewCard(navController =  navController)
                            //navController =  navController
                        }

                        composable("SpendAdd") {
                            AddSpendingMoney(navController =  navController)
                        }
                        composable("IncomeAdd") {
                            AddIncomeMoney(navController =  navController, memberViewModel)
                        }


                        composable("CrewNameAdd") {
                            CrewName(navController = navController, memberViewModel)
                        }
                        composable("CrewDesAdd"){
                            CrewDes(navController = navController, memberViewModel)
                        }
                        composable("CrewTagAdd") {
                            CrewTag(navController = navController, memberViewModel)
                        }
                        composable("CrewTarAdd") {
                            CrewTar(navController = navController, memberViewModel)
                        }
                        composable("CrewPeopleAdd") {
                            CrewPeople(navController = navController, memberViewModel)
                        }
                    }
                }
            }
        }
    }
}
