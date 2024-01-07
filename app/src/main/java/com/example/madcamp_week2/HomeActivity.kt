package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import bigPlainTextStyle
import bigTitleTextStyle
import com.example.madcamp_week2.ui.theme.MadCamp_week2Theme
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import com.example.madcamp_week2.ui.theme.WhiteBox
import plainTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
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
fun HomeScreen(navController: NavHostController) {
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
            AppLogo()
            TotalIncome()
            CrewBar(navController)
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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4,395,847원",
                    style = bigPlainTextStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(PointBackground)
                )
            }
        }
    }
}

@Composable
fun CrewBar(navController: NavHostController) {
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
            ) {
                Text(text = "+",
                    style = plainTextStyle,
                    color = Black)
            }
        }
        LazyRow (
            modifier = Modifier
                .fillMaxWidth()
        )   {
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
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = WhiteBox,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(7.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(30.dp)))

        ) {
            Text(
                text = "지출 추가하기",
                style = plainTextStyle,
                modifier = Modifier
                    .padding(30.dp)
            )

        }
    }
}

@Composable
fun AddIncome() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.Center,
            ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = WhiteBox,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(7.dp, shape = MaterialTheme.shapes.small.copy(all = CornerSize(30.dp)))

        ) {
            Text(
                text = "자산 추가하기",
                style = plainTextStyle,
                modifier = Modifier
                    .padding(30.dp)
            )

        }
    }
}

@Composable
fun CrewCard() {
    val navController = rememberNavController()
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
                    onClick = { navController.navigate("Home") },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                        .height(35.dp)
                ) {
                    Text(
                        text = ">",
                        color = Black
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "배민과 천생연분인 사람들")
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircleProgress()
            }
        }
    }
}

@Composable
fun CircleProgress() {
    // var progress by remember { mutableStateOf(0f) }
    var progress = 0.5f
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
                progress = progress,
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
                    text = "400,000",
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