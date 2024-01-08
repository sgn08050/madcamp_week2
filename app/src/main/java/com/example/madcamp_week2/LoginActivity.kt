package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcamp_week2.serverInterface.components.kakaoLogin
import com.example.madcamp_week2.serverInterface.components.loginPost
import com.example.madcamp_week2.serverInterface.components.registerPost
import com.example.madcamp_week2.serverInterface.loginInformation
import com.example.madcamp_week2.ui.theme.MadCamp_week2Theme
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import com.example.madcamp_week2.ui.theme.WhiteBox
import middleTitleTextStyle
import plainTextStyle


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadCamp_week2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {

    var idValue = remember{ mutableStateOf("")}
    var passwordValue = remember{ mutableStateOf("")}
    var loginState = remember{ mutableStateOf(false)}
    var registerState = remember{ mutableStateOf(false)}
    var kakaoLoginState = remember{ mutableStateOf(false)}


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(40.dp)
                .shadow(10.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(70.dp))),
            colors = CardDefaults.cardColors(
                containerColor = WhiteBox
            ),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(70.dp))
        ) {
            Column (
                modifier = Modifier
                    .padding(30.dp)
            ) {
                Row (
                    modifier = Modifier
                        .padding( bottom = 20.dp, top = 30.dp)
                ) {
                    Text(
                        text = "로그인",
                        style = middleTitleTextStyle,
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "아이디",
                                style = plainTextStyle
                            )
                            var text by remember { mutableStateOf(TextFieldValue()) }
                            TextField(
                                value = idValue.value,
                                onValueChange = { newId -> idValue.value = newId},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = MaterialTheme.shapes.small.copy(all = CornerSize(0.dp))
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "비밀번호",
                                style = plainTextStyle
                            )
                            var text by remember { mutableStateOf(TextFieldValue()) }
                            TextField(
                                value = passwordValue.value,
                                onValueChange = { newId -> passwordValue.value = newId},
                                label = { Text(
                                    text = "",
                                    style = plainTextStyle
                                ) } ,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = MaterialTheme.shapes.small.copy(all = CornerSize(0.dp))
                            )
                        }
                    }
                }

                Box {
                    Column (
                        modifier = Modifier
                            .padding( top = 30.dp)
                    ){
                        Button(
                            onClick = {loginState.value = true},
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PointBackground
                            ),
                            interactionSource = remember { MutableInteractionSource() }) {
                            Text("로그인 하기")
                        }

                        Button(
                            onClick = { registerState.value = true },
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PointBackground
                            ),
                            interactionSource = remember { MutableInteractionSource() }) {
                            Text("회원가입")
                        }

                        Button(
                            onClick = {kakaoLoginState.value = true},
                            modifier = Modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PointBackground
                            ),
                            interactionSource = remember { MutableInteractionSource() }) {
                            Text("카카오톡으로 로그인하기")
                        }

                    }
                }
            }
        }
    }

    // If loginState changes true, post the data and navigate to other page.
    if (loginState.value){
        loginPost(loginInformation = loginInformation(idValue.value, passwordValue.value), navController)
        loginState.value = false
    }

    if (registerState.value){
        registerPost(loginInformation = loginInformation(idValue.value, passwordValue.value), navController)
        loginState.value = false
    }

    if(kakaoLoginState.value){
        kakaoLogin(navController)
        kakaoLoginState.value = false
    }

}