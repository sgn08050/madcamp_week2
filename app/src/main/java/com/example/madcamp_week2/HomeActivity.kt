package com.example.madcamp_week2

import android.os.Bundle
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Preview
@Composable
fun HomeScreen() {
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
fun CrewBar() {
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
    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(bottom = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.)
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
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier
                ) {
                    Text(
                        text = ">",
                        style = plainTextStyle,
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
        }
    }
}