package com.example.madcamp_week2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import plainTextStyle
import smallPlainTextStyle

// navController: NavHostController
@Composable
fun EachCrewCard(navController: NavHostController) {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor)
    ) {
        item {
            CrewCardName()
            CrewCardMoney()
        }
    }
}


@Composable
fun CrewCardName() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "test",
            style = bigTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
    }
}

@Composable
fun CrewCardMoney() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "95,847",
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
    }
}