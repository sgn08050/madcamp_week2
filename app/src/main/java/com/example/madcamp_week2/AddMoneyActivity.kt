package com.example.madcamp_week2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import plainTextStyle

class AddMoneyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
            }
        }
    }
}


var totalMoneyInt: Int = totalMoney.toInt()

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

@Composable
fun AddSpendingMoney(navController: NavHostController) {
    var buttonList by remember { mutableStateOf(listOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        var spendMoney by remember { mutableStateOf("") }
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
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
                TextField(
                    value = spendMoney,
                    onValueChange = { spendMoney = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    placeholder = { Text(text = "얼마를 사용했나요?") },
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
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 30.dp)
                    .height(60.dp),
            ) {
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
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                        onClick = { buttonList = buttonList + "#식비" },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#식비")
                    }
                    Button(
                        onClick = { buttonList = buttonList + "#교통비" },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#교통비")
                    }
                    Button(
                        onClick = { buttonList = buttonList + "#간식비" },
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
                        onClick = { buttonList = buttonList + "#꾸밈비" },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#꾸밈비")
                    }
                    Button(
                        onClick = { buttonList = buttonList + "#여가비" },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#여가비")
                    }
                    Button(
                        onClick = { buttonList = buttonList + "#생활비" },
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
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(60.dp),
            ) {
                LazyRow {
                    items(TagList) { tagLabel ->
                        Button(
                            onClick = { TagList = TagList.toMutableList().apply {
                                remove(tagLabel)
                            } },
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

@OptIn(ExperimentalMaterial3Api::class)
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                TextField(
                    value = incomeMoney,
                    onValueChange = { incomeMoney = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    placeholder = { Text(text = "얼마를 자산에 추가할까요?") },
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