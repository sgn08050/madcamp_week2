package com.example.madcamp_week2.serverInterface.components.POST


import androidx.navigation.NavController
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.LoginResponse
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.classComponents.loginInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun kakaoLoginPost(loginInformation: loginInformation, navController: NavController, memberViewModel: memberViewModel){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.postKakaoLoginRequest(loginInformation).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    responseBody.member_id?.let { memberViewModel.updateMember_id(it) }
                    responseBody.result?.let {
                        if (it === "1") navController.navigate("MoneyCreate")
                        else navController.navigate("Home")
                    }
                }
            } else {

            }
        }
        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        }
    })
}
