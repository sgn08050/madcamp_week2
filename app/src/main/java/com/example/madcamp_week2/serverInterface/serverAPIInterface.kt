package com.example.madcamp_week2.serverInterface

import retrofit2.Call
import retrofit2.http.*

data class ResponseDC(var result: String? = null)
interface serverAPIInterface {
    @POST("/register/")
    fun postRegisterRequest(@Body loginInformation: loginInformation): Call<ResponseDC>

    @POST("/login/app")
    fun postAppLoginRequest(@Body loginInformation: loginInformation): Call<ResponseDC>

    @POST("/login/kakao/")
    fun postKakaoLoginRequest(@Body loginInformation: loginInformation): Call<ResponseDC>
}