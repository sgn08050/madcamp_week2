package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.MadCamp_week2Theme
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import plainTextStyle

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadCamp_week2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CrewBar()
                }
            }
        }
    }
}

@Preview
@Composable
fun homeActivity() {
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(TotalBackgroundColor),
    ) {
        item {
            AppLogo()
            TotalIncome()
            CrewBar()
            AddSpending()
            AddIncome()
        }
    }
}

@Composable
fun AppLogo() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(text = "로고 들어갈 자리")
    }
}

@Composable
fun TotalIncome() {
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ){
            Text(
                text = "총 자산",
                style = bigTitleTextStyle
            )
        }
        Row (
            modifier = Modifier
                .background(PointBackground)

        ) {
            Text(
                text = "4,395,847원",
                style = bigPlainTextStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CrewBar() {
    Column {
        Row {
            Text(text = "내 모임")
        }
        LazyRow {
            item {
                CrewCard()
            }
            item {
                CrewCard()
            }
            item {
                CrewCard()
            }
        }
    }
}

@Composable
fun AddSpending() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width=314.dp, height=77.dp)
    ) {
        Text(text = "지출 추가하기")

    }
}

@Composable
fun AddIncome() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width=314.dp, height=77.dp)
    ) {
        Text(text = "자산 추가하기")

    }
}

@Composable
fun CrewCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width=194.dp, height=247.dp)
    ) {
        Text(text = "배민과 천생연분인 사람들")
    }
}