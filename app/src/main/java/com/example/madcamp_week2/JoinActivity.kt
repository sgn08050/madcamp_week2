package com.example.madcamp_week2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInform(navController: NavHostController) {
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
                var userID by remember { mutableStateOf("") }
                TextField(
                    value = userID,
                    onValueChange = { userID = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
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
            var test = false
            Button(
                onClick = { /* 중복체크 조건문 */ },
                modifier = Modifier
            ) {
                Text(
                    text = "아이디 중복 체크"
                )
            }
            Text(
                text = "테스트 테스트",
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
                var userPW by remember { mutableStateOf("") }
                TextField(
                    value = userPW,
                    onValueChange = { userPW = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
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
                    navController.navigate("Home")
                    navController.navigate("MoneyCreate")
                },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserIncome(navController: NavHostController) {
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
        var initialTotalMoney by remember { mutableStateOf("") }
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
                // navController.navigate("CrewDesHome")
                onClick = {
                    totalMoney = initialTotalMoney
                    navController.navigate("Home")
                    },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}