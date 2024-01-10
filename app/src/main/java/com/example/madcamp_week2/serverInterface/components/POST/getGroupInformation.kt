package com.example.madcamp_week2.serverInterface.components.POST

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.madcamp_week2.serverInterface.GroupInformationResponse
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getGroupInformation(inputassetsgroupInformation: MutableState<assetsgroupInformation>, outputassetsgroupInformation: MutableState<assetsgroupInformation>, getCheck: MutableState<Boolean>){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.getGroupInformation(inputassetsgroupInformation.value).enqueue(object : Callback<GroupInformationResponse> {
        override fun onResponse(call: Call<GroupInformationResponse>, response: Response<GroupInformationResponse>) {
            if (response.isSuccessful) {
                response.body()?. let{
                    val result = assetsgroupInformation(it.member_id, it.assetsgroup_id, it.assetsgroupname, it.assetsgroupgoal, it.categories, it.targetasset, it.currentasset)
                    outputassetsgroupInformation.value = result
                    getCheck.value = true
                }
            }
            else {

            }
        }
        override fun onFailure(call: Call<GroupInformationResponse>, t: Throwable) {
        }
    })
}