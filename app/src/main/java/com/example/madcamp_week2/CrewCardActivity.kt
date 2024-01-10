package com.example.madcamp_week2


import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.components.POST.getAllGroups
import com.example.madcamp_week2.ui.theme.ProgressedRed
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import plainTextStyle
import smallPlainTextStyle

// ※ sendCrewData format : [ Name, Destination, Group Tag, Target Money, Friends ]

@Composable
fun EachCrewCard(navController: NavHostController) {

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
                CrewCardName(navController, groupInformation.value)
                CrewCardMoney(groupInformation.value)
            }
        }
}


@Composable
fun CrewCardName(navController: NavHostController, assetsgroupInformation: assetsgroupInformation) {
    Column (
        modifier = Modifier
            .padding(horizontal = 30.dp)
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
            text = if (assetsgroupInformation.assetsgroupname === "") "이름이 입력되지 않았습니다." else assetsgroupInformation.assetsgroupname,
            style = bigTitleTextStyle,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
        )
        Text(
            text = if (assetsgroupInformation.assetsgroupgoal === "") "목적이 입력되지 않았습니다." else assetsgroupInformation.assetsgroupgoal,
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
        var tagList = "[#여가비, #교통비]"
        var finalList = tagList.split(",").map { it.trim(' ', '[').trimEnd(']') }
        LazyRow {
            items(finalList.size) { item ->
                Text(
                    text = "${finalList[item]}",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp),
                    style = plainTextStyle
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(1.dp),
            color = Color.Black
        )
    }
}

@Composable
fun CrewCardMoney(assetsgroupInformation: assetsgroupInformation) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = assetsgroupInformation.currentasset.toString(),

                    style = bigTitleTextStyle
                )
                Text(
                    text = " 원",
                    style = bigPlainTextStyle
                )
                Text(text = "남았습니다.",
                    style = plainTextStyle,
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                )
            }
        }
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 20.dp)
        ) {
            var progress = CalculateMoney(assetsgroupInformation.targetasset, assetsgroupInformation.currentasset)[1]
            LinearProgressBar(progress = progress)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "목표 제한 금액 : ",
                    style = smallPlainTextStyle,
                    color = Color.Gray
                )
                Text(
                    text = assetsgroupInformation.targetasset.toString(),
                    style = smallPlainTextStyle,
                    color = Color.Gray
                )
                Text(text = " 원",
                    style = smallPlainTextStyle,
                    modifier = Modifier
                        .padding(horizontal = 2.dp),
                    color = Color.Gray
                )
            }
        }
    }
}

fun CalculateMoney(TarMoney:Int, RemainMoney: Int) : List<Float> {

    var RemainMoneyRate = (TarMoney - RemainMoney).toFloat()/TarMoney.toFloat()

    return listOf((TarMoney - RemainMoney).toFloat(), RemainMoneyRate)
}


@Composable
fun LinearProgressBar(progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.small),
            color = ProgressedRed
        )
    }
}

@Composable
fun RankingPeople() {

}




