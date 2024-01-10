package com.example.madcamp_week2.serverInterface.components.POST


import androidx.navigation.NavController
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.AssetsGroupResponse
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun assetsgroupPost(assetsgroupInformation: assetsgroupInformation, navController: NavController, memberViewModel: memberViewModel){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)

    server.postAssetsGroup(assetsgroupInformation).enqueue(object : Callback<AssetsGroupResponse> {
        override fun onResponse(call: Call<AssetsGroupResponse>, response: Response<AssetsGroupResponse>) {
            if (response.isSuccessful) {
                response.body()?.let{
                        responseBody ->
                    responseBody.assetsgroup_id?.let { memberViewModel.updateGroup_id(it) }
                }
                navController.navigate("CrewPeopleAdd")
            } else {
                navController.navigate("CrewPeopleAdd")
            }
        }
        override fun onFailure(call: Call<AssetsGroupResponse>, t: Throwable) {
        }
    })
}