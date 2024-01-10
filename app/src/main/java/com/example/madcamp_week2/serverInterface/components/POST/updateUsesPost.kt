package com.example.madcamp_week2.serverInterface.components.POST


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.ResponseDC
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupmemberpair
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun updateUsesPost(updateGroupInformationList: List<assetsgroupInformation>, use: Int, navController: NavController, memberViewModel: memberViewModel){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)
    val updateUseList = mutableListOf<assetsgroupmemberpair>()
    memberViewModel.member_id.value?.let{
        updateGroupInformationList.forEach{
            ind -> updateUseList.add(assetsgroupmemberpair(ind.assetsgroup_id, ind.member_id, ind.targetasset, ind.currentasset - use))
        }
        server.updateUses(updateUseList).enqueue(object : Callback<ResponseDC> {
            override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                if (response.isSuccessful) {
                } else {
                }
            }
            override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
            }
        })
    }
}
