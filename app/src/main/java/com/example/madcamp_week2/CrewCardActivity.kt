package com.example.madcamp_week2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.ProgressedRed
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import com.example.madcamp_week2.ui.theme.UnProgressedGray
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import middleTitleTextStyle
import plainTextStyle
import smallPlainTextStyle
import java.util.concurrent.TimeUnit

// ※ sendCrewData format : [ Name, Destination, Group Tag, Target Money, Friends ]


// navController: NavHostController


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
            CrewCardName(sendCrewData)
            CrewCardMoney(sendCrewData)
        }
    }
}


@Composable
fun CrewCardName(crewData:List<String>?) {
    Column (
        modifier = Modifier
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = crewData?.get(0).takeIf { it?.isNotBlank() == true } ?: "이름이 입력되지 않았습니다.",
            style = bigTitleTextStyle,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
        )
        Text(
            text = crewData?.get(1).takeIf { it?.isNotBlank() == true } ?: "목적이 입력되지 않았습니다. ",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
        var tagList = crewData?.get(2).takeIf { it?.isNotBlank() == true } ?: "지출 태그가 없습니다."
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
fun CrewCardMoney(crewData:List<String>?) {
    Column (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "95,847",
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
                .padding(top = 10.dp)
        ) {
            var progress by remember { mutableStateOf(0.5f) }
            var markerPosition by remember { mutableStateOf(0.7f) }
            LinearProgressBar(progress = progress, markerPosition = markerPosition)
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
                    text = crewData?.get(3).takeIf { it?.isNotBlank() == true } ?: "-",
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

@Composable
fun LinearProgressBar(progress: Float, markerPosition: Float) {
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




