package com.example.madcamp_week2

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigTitleTextStyle
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.classComponents.assetsInformation
import com.example.madcamp_week2.serverInterface.classComponents.loginInformation
import com.example.madcamp_week2.serverInterface.components.POST.idCheckPost
import com.example.madcamp_week2.serverInterface.components.POST.registerAssetsPost
import com.example.madcamp_week2.serverInterface.components.POST.registerPost
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle

var duplicateState = mutableStateOf(false) // 아이디 중복체크 성공여부 저장

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserInform(navController: NavHostController, memberViewModel: memberViewModel) {

    var userID by remember { mutableStateOf("") }
    var userPW by remember { mutableStateOf("") }
    var doubleCheckState by remember {mutableStateOf(false)}  // 아이디 중복 체크를 할 수 있으면 true, 아니면 false (onclick 변화)
    var clickedState by remember {mutableStateOf(false)} // 아이디 중복 체크를 클릭했으면 true, 아니면 false
    var completeState by remember {mutableStateOf(false)} // 다음으로 넘어갈 수 있으면 true, 아니면 false

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        Text(
            text = "회원가입",
            style = bigTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "아이디 입력",
                    style = middleTitleTextStyle
                )
            }
            Column {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = userID,
                    onValueChange = { userID = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .padding(start = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = {
                    doubleCheckState = true
                          },
                modifier = Modifier
            ) {
                Text(
                    text = "아이디 중복 체크"
                )
            }
            Text(
                text =
                if (clickedState) {
                    if(duplicateState.value) "사용 가능한 아이디입니다."
                    else "이미 사용중인 아이디입니다."
                }
                else{
                    "아이디 중복 체크를 해주세요."
                },
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "비밀번호 입력",
                    style = middleTitleTextStyle
                )
            }
            Column {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = userPW,
                    onValueChange = { userPW = it },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .padding(end = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            completeState = true
                        }
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    completeState = true
                },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

    // ID doubleCheck
    if(doubleCheckState){
        idCheckPost(loginInformation = loginInformation(userID, userPW, 0), duplicateState)
        clickedState = true
        doubleCheckState = false
    }

    // Register user in database
    if (duplicateState.value && completeState){
        registerPost(loginInformation(userID, userPW, 0), navController, memberViewModel)
        duplicateState.value = false
        completeState = false
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserIncome(navController: NavHostController, memberViewModel: memberViewModel) {

    var initialTotalMoney by remember { mutableStateOf("") }
    var registerState by remember{mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        Text(
            text = "나의 자산 입력",
            style = bigTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        Text(
            text = "현재 나의 자산은 얼마인가요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                TextField(
                    value = initialTotalMoney,
                    onValueChange = { initialTotalMoney = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "원",
                    style = middleTitleTextStyle
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(

                onClick = {
                    var moneyState = initialTotalMoney.toIntOrNull()
                    if (initialTotalMoney.isEmpty() || moneyState == null) totalMoney = "0"
                    else totalMoney = initialTotalMoney
                    registerState = true
                    navController.navigate("Home")
                },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

    if(registerState){
        val member_id: String? = memberViewModel.member_id.value
        member_id?.let{
            registerAssetsPost(assetsInformation(it, totalMoney.toInt(), true), navController)
        }
        registerState = false
    }
}