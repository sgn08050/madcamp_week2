package com.example.madcamp_week2.serverInterface.components


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.loginInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun registerPost(loginInformation: loginInformation, navController: NavController){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.postRegisterRequest(loginInformation).enqueue(object : Callback<ResponseDC> {
        override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
            if (response.isSuccessful) {
                navController.navigate("Home")
            } else {

            }
        }
        override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
        }
    })
}
