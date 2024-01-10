package com.example.madcamp_week2.serverInterface.components.POST

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.GroupInformationResponse
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.classComponents.member
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getAllGroups(memberViewModel: memberViewModel, groups: MutableState<List<assetsgroupInformation>>, check: MutableState<Boolean>){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)
    memberViewModel.member_id.value?.let{
        value -> server.getAllGroups(member(value)).enqueue(object : Callback<List<GroupInformationResponse>> {
            override fun onResponse(call: Call<List<GroupInformationResponse>>, response: Response<List<GroupInformationResponse>>) {
                if (response.isSuccessful) {
                    val groupsIds = response.body()?.map { assetsgroupInformation(it.member_id, it.assetsgroup_id, it.assetsgroupname, it.assetsgroupgoal, emptyList(), it.targetasset, it.currentasset) } ?: listOf()
                    groups.value = groupsIds
                    check.value = true
                } else {

                }
            }
            override fun onFailure(call: Call<List<GroupInformationResponse>>, t: Throwable) {
            }
        })
    }
}