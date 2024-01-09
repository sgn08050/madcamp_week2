package com.example.madcamp_week2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import plainTextStyle
import smallPlainTextStyle

class AddMoneyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
            }
        }
    }
}


var totalMoneyInt: Int = totalMoney.toInt()

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddSpendingMoney(navController: NavHostController) {
    var spendMoney by remember { mutableStateOf("") }
    var buttonList by remember { mutableStateOf(listOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .height(35.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ui_return),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp) // 이미지 크기
                        .background(
                            color = Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
        Text(
            text = "지출 추가하기",
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
            Column {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = spendMoney,
                    onValueChange = { spendMoney = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    placeholder = { Text(text = "얼마를 사용했나요?") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            cardData.add(spendMoney)
                            navController.navigate("Home")
                        }
                    )
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
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PointBackground,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 30.dp)
                    .height(80.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()) {
                    Text(
                        text = "선택된 지출 태그",
                        style = smallPlainTextStyle,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(start = 10.dp)
                    )
                }
                LazyRow {
                    items(buttonList) { buttonLabel ->
                        Button(
                            onClick = { buttonList = buttonList.toMutableList().apply {
                                remove(buttonLabel)
                            } },
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                            ) {
                                Text(text = buttonLabel)
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PointBackground,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (!buttonList.contains("#식비"))
                                buttonList += "#식비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#식비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#교통비"))
                                buttonList += "#교통비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#교통비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#간식비"))
                                buttonList += "#간식비"
                                  },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#간식비")
                    }
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            if (!buttonList.contains("#꾸밈비"))
                                buttonList += "#꾸밈비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#꾸밈비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#여가비"))
                                buttonList += "#여가비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#여가비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#생활비"))
                                buttonList += "#생활비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#생활비")
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PointBackground,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(80.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()) {
                    Text(
                        text = "사용자 태그",
                        style = smallPlainTextStyle,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(start = 10.dp)
                    )
                }
                LazyRow {
                    items(usrTagList) { tagLabel ->
                        Button(
                            onClick = {
                                if (!buttonList.contains("$tagLabel"))
                                    buttonList += "$tagLabel"
                            },
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(4.dp)
                            ) {
                                Text(text = tagLabel)
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            val spendMoneyInt: Int = if (spendMoney.isNotEmpty()) {
                spendMoney.toInt()
            } else {
                0
            }
            Button(
                onClick = {
                    totalMoneyInt -= spendMoneyInt
                    totalMoney = totalMoneyInt.toString()

                    CalculateMoneyTag(selectedTag = buttonList, spendMoneyInt)
                    navController.navigate("Home")
                },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "완료")
            }
        }
    }
}

fun CalculateMoneyTag(selectedTag: List<String>, spendMoney: Int) {
    var index = 0
    for (eachCardData in cardDataList) {
        var eachCardTag = eachCardData.get(2)
        for (tag in selectedTag) {
            if (eachCardTag.contains(tag)) {
                var presentSpendMoney = eachCardData[eachCardData.size - 1].toInt()
                presentSpendMoney  += spendMoney
                eachCardData[eachCardData.size - 1] = presentSpendMoney.toString()
                cardDataList[index] = eachCardData
            }
        }
        index++
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddIncomeMoney(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        var incomeMoney by remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .height(35.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ui_return),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp) // 이미지 크기
                        .background(
                            color = Color.Transparent,
                            shape = CircleShape
                        )
                )
            }
        }
        Text(
            text = "자산 추가하기",
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
            Column {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = incomeMoney,
                    onValueChange = { incomeMoney = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    placeholder = { Text(text = "얼마를 자산에 추가할까요?") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            cardData.add(incomeMoney)
                            navController.navigate("Home")
                        }
                    )
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
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            val incomeMoneyInt: Int = if (incomeMoney.isNotEmpty()) {
                incomeMoney.toInt()
            } else {
                0
            }
            Button(
                onClick = {
                    totalMoneyInt += incomeMoneyInt
                    totalMoney = totalMoneyInt.toString()
                    navController.navigate("Home")
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "완료")
            }
        }
    }
}

