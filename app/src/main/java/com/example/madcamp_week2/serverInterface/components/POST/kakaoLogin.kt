package com.example.madcamp_week2.serverInterface.components.POST

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.classComponents.loginInformation
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

@Composable
fun kakaoLogin(navController: NavController, memberViewModel: memberViewModel){

    // login with kakaoaccount
    val context = LocalContext.current

    KakaoSdk.init(context, "167d1f1e87c01a84febc3aa3793db41b")

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("login fail", "fail", error)
        } else if (token != null) {

            UserApiClient.instance.me { user, getError ->
                if (getError != null) {
                    Log.d("login", "fail error")
                }
                else if (user != null) {
                    kakaoLoginPost(loginInformation = loginInformation(user.kakaoAccount?.profile?.nickname.toString(), user.id.toString()), navController = navController, memberViewModel)
                }
            }

        }
    }

    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {

        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                navController.navigate("Login")
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }
                // If there is an error in app, try account.
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)

            } else if (token != null) {
                navController.navigate("Login")
            }
        }
    }
    // It there is no app, try account.
    else {
        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
    }
}