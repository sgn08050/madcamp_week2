package com.example.madcamp_week2.serverInterface.components.POST


import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.AssetsResponse
import com.example.madcamp_week2.serverInterface.classComponents.member
import com.example.madcamp_week2.serverInterface.serverAPIInterface
import com.example.madcamp_week2.totalMoney
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



fun getAssetsPost(memberViewModel: memberViewModel, incomeState: MutableState<Boolean>, money: MutableState<String>){

    val url = "http://172.10.8.235"

    val retrofit = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var server = retrofit.create(serverAPIInterface::class.java)
    memberViewModel.member_id.value?.let{
            value -> server.getAssets(member(value)).enqueue(object : Callback<AssetsResponse> {
        override fun onResponse(call: Call<AssetsResponse>, response: Response<AssetsResponse>) {
            if (response.isSuccessful) {
                response.body()?.let{
                        responseBody ->
                    responseBody.assets?.let {
                        Log.d("asdf", it.toString())
                        money.value = it.toString()
                        incomeState.value = true
                    }
                    Log.d("asdf", "not")
                }
            } else {

            }
        }
        override fun onFailure(call: Call<AssetsResponse>, t: Throwable) {
        }
    })
    }
}