package com.example.madcamp_week2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.MadCamp_week2Theme
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import com.example.madcamp_week2.ui.theme.WhiteBox
import plainTextStyle
import androidx.compose.runtime.*
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.components.POST.getAllGroups
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.clipRect
import com.example.madcamp_week2.serverInterface.components.POST.getAssetsPost
import com.example.madcamp_week2.ui.theme.Brown40
import com.example.madcamp_week2.ui.theme.ProgressedRed
import com.example.madcamp_week2.ui.theme.UnProgressedGray
import middleTitleTextStyle
import smallPlainTextStyle

class HomeActivity : ComponentActivity() {
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

//navController: NavHostController
// @Preview
@Composable
fun HomeScreen(navController: NavHostController, memberViewModel: memberViewModel) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFD0C1B9), Color.Transparent),
                    startY = 0.0f,
                    endY = 300.0f
                )
            )
    ) {
        item {
            TotalIncome(memberViewModel)
            CrewBar(navController, memberViewModel)
            AddSpending(navController)
            AddIncome(navController)
        }
    }
}


var totalMoney = "아직 설정하지 않았습니다"
var incomeState = mutableStateOf(false)
var money = mutableStateOf("")
@SuppressLint("UnrememberedMutableState")
@Composable
fun TotalIncome(memberViewModel: memberViewModel) {

    getAssetsPost(memberViewModel, incomeState, money)
    if(incomeState.value) {
        totalMoney = money.value
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Text(
                    text = "총 자산",
                    style = bigTitleTextStyle
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Text(
                            text = money.value,
                            style = bigPlainTextStyle,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(PointBackground)
                        )
                        Text(
                            text = " 원",
                            style = bigTitleTextStyle,
                            modifier = Modifier
                                .background(PointBackground)
                        )

                    }
                }
            }
        }
    }
}

var cardDataList = mutableListOf<MutableList<String>>()
var sendCrewData = mutableListOf<String>()

@SuppressLint("UnrememberedMutableState")
@Composable
fun CrewCard(navController: NavController, memberViewModel: memberViewModel, crewData: assetsgroupInformation) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = WhiteBox
            ),
            modifier = Modifier
                .size(width = 194.dp, height = 247.dp)
                .shadow(7.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(30.dp)))
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        sendCrewData.clear()
                        sendCrewData.add(crewData.assetsgroupname)
                        groupInformation.value = crewData
                        navController.navigate("EachCrewCard")
                    },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.height(35.dp)
                ) {
                    Text(text = ">", color = Black)
                }

            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = crewData.assetsgroupname)
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircleProgress(crewData)
            }
        }
    }
}
var check = mutableStateOf(false)
var myAssetsGroups = mutableStateOf(listOf<assetsgroupInformation>())
var groupInformation = mutableStateOf(assetsgroupInformation("", "", "", "", emptyList(), 0, 0))

@SuppressLint("UnrememberedMutableState")
@Composable
fun CrewBar(navController: NavHostController, memberViewModel: memberViewModel) {
  
    getAllGroups(memberViewModel, myAssetsGroups, check)
    if(check.value) {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        )   {
            Text(
                text = "내 모임",
                style = bigTitleTextStyle
            )
            Button(onClick = { navController.navigate("CrewNameAdd") },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .border(2.dp, PointBackground, shape = CircleShape)
            ) {
                Text(text = "+",
                    style = plainTextStyle,
                    color = Black)
            }
        }
        Surface (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(
                    modifier = Modifier
                        .matchParentSize()
                ) {
                    val bigRectHeight = 140.dp
                    val smallRectHeight = 15.dp
                    clipRect {
                        drawRect(
                            color = TotalBackgroundColor,
                            size = Size(size.width, size.height - bigRectHeight.toPx())
                        )
                        // 베이지색 도형 그리기
                        drawRect(
                            color = PointBackground,
                            size = Size(size.width, bigRectHeight.toPx()),
                            topLeft = Offset(0f, size.height - bigRectHeight.toPx())
                        )
                        // Y축 방향으로 이동하여 얇은 베이지색 도형 그리기
                        drawRect(
                            color = PointBackground,
                            size = Size(size.width, smallRectHeight.toPx()),
                            topLeft = Offset(0f, size.height - bigRectHeight.toPx() - 70f)  // Y축 방향으로 위로 이동
                        )
                    }
                }
                LazyRow {
                    items(count = myAssetsGroups.value.size) {
                                    index ->
                                    CrewCard(
                                        navController = navController,
                                        memberViewModel,
                                        crewData = myAssetsGroups.value[index]
                                    )
                                }
                    }
                }
        }
    }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//navController: NavController
@Composable
fun AddSpending(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Brown40,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("SpendAdd") }
                .shadow(7.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(30.dp)))
        ) {
            //navController.navigate("SpendAdd")
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)  // 첫 번째 Column이 남은 공간을 모두 차지하도록 합니다.
                ) {
                    Text(
                        text = "지출 추가하기",
                        style = plainTextStyle,
                        modifier = Modifier
                            .padding(30.dp)
                    )
                }
                Column (
                    modifier = Modifier
                        .align(Alignment.Bottom)  // 두 번째 Column을 아래로 정렬하여 오른쪽 끝에 배치합니다.
                ) {
                    Text(
                        text = ">",
                        style = plainTextStyle,
                        modifier = Modifier
                            .padding(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AddIncome(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
            ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Brown40,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("IncomeAdd") }
                .shadow(7.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(30.dp)))

        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)  // 첫 번째 Column이 남은 공간을 모두 차지하도록 합니다.
                ) {
                    Text(
                        text = "자산 추가하기",
                        style = plainTextStyle,
                        modifier = Modifier
                            .padding(30.dp)
                    )
                }
                Column (
                    modifier = Modifier
                        .align(Alignment.Bottom)  // 두 번째 Column을 아래로 정렬하여 오른쪽 끝에 배치합니다.
                ) {
                    Text(
                        text = ">",
                        style = plainTextStyle,
                        modifier = Modifier
                            .padding(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CircleProgress(crewData: assetsgroupInformation) {
    // var progress by remember { mutableStateOf(0f) }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                progress = CalculateMoney(crewData.targetasset, crewData.currentasset)[1],
                modifier = Modifier
                    .size(150.dp),
                color = ProgressedRed,
                strokeWidth = 7.dp,
            )
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = crewData.currentasset.toString(),

                    style = middleTitleTextStyle
                )
                Text(
                    text = " 원",
                    style = plainTextStyle
                )
                Text(text = "남음",
                    style = smallPlainTextStyle,
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                )
            }
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "총 ",
                    style = smallPlainTextStyle,
                    color = UnProgressedGray
                )
                Text(
                    text = crewData.targetasset.toString(),
                    style = smallPlainTextStyle,
                    color = UnProgressedGray
                )
                Text(text = "원 목표",
                    style = smallPlainTextStyle,
                    color = UnProgressedGray,
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                )
            }
        }
    }
}