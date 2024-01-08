package com.example.madcamp_week2.serverInterface.components.POST

import androidx.navigation.NavController
import com.example.madcamp_week2.serverInterface.CategoryResponse
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.classComponents.categoryInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun categoryPost(categoryInformation: categoryInformation, navController: NavController){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.postCategory(categoryInformation).enqueue(object : Callback<CategoryResponse> {
        override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
            if (response.isSuccessful) {

            } else {
            }
        }
        override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
        }
    })
}