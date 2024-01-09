package com.example.madcamp_week2.serverInterface.components.POST


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.classComponents.loginInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun idCheckPost(loginInformation: loginInformation, duplicateState: MutableState<Boolean>){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.postIdCheck(loginInformation).enqueue(object : Callback<ResponseDC> {
        override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {

            // There is same ID.
            if (response.isSuccessful) {
                duplicateState.value = true
            }

            // There is no same ID.
            else {
                duplicateState.value = false
            }
        }
        override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
        }
    })
}