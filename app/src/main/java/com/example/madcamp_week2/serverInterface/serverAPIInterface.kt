package com.example.madcamp_week2.serverInterface

import com.example.madcamp_week2.serverInterface.classComponents.assetsInformation
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupmemberpair
import com.example.madcamp_week2.serverInterface.classComponents.categoryInformation
import com.example.madcamp_week2.serverInterface.classComponents.loginInformation
import retrofit2.Call
import retrofit2.http.*

open class ResponseDC(
    var result: String? = null
)

data class LoginResponse(
    var member_id: String? = null
) : ResponseDC()

data class AssetsGroupResponse(
    var assetsgroup_id: String? = null
) : ResponseDC()

data class CategoryResponse(
    var category_id: String? = null
) : ResponseDC()

data class MemberResponse(
    var id: String
) : ResponseDC()

interface serverAPIInterface {

    @POST("/register/idcheck")
    fun postIdCheck(@Body loginInformation: loginInformation): Call<ResponseDC>

    @POST("/register/")
    fun postRegisterRequest(@Body loginInformation: loginInformation): Call<LoginResponse>

    @POST("/login/app/")
    fun postAppLoginRequest(@Body loginInformation: loginInformation): Call<LoginResponse>

    @POST("/login/kakao/")
    fun postKakaoLoginRequest(@Body loginInformation: loginInformation): Call<LoginResponse>

    @POST("/assetsgroup/information")
    fun postAssetsGroup(@Body assetsgroupInformation: assetsgroupInformation): Call<AssetsGroupResponse>

    @POST("/category/information")
    fun postCategory(@Body categoryInformation: categoryInformation): Call<CategoryResponse>

    @POST("/assetsgroupmemberpair/information")
    fun postAssetsGroupMemberPair(@Body assetsgroupmemberpair: assetsgroupmemberpair): Call<ResponseDC>

    @POST("/register/assets")
    fun postRegisterAssets(@Body assetsInformation: assetsInformation): Call<ResponseDC>

    @GET("/members/all")
    fun getAllMembers(): Call<List<MemberResponse>>
}