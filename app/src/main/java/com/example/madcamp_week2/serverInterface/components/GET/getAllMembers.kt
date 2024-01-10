package com.example.madcamp_week2.serverInterface.components.GET

import androidx.compose.runtime.MutableState
import com.example.madcamp_week2.serverInterface.MemberResponse
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getAllMembers(members: MutableState<List<String>>){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.getAllMembers().enqueue(object : Callback<List<MemberResponse>> {
        override fun onResponse(call: Call<List<MemberResponse>>, response: Response<List<MemberResponse>>) {
            if (response.isSuccessful) {
                val memberIds = response.body()?.map { it.id } ?: listOf()
                members.value = memberIds
            } else {

            }
        }
        override fun onFailure(call: Call<List<MemberResponse>>, t: Throwable) {
        }
    })
}