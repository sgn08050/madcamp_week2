package com.example.madcamp_week2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

class AddCrewActivity : ComponentActivity() {
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

@Composable
fun CrewAddScreen(navController: NavHostController) {
    Column {
        CrewName()
        CrewDes()
        CrewTag()
        CrewDest()
        CrewPeople()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewName() {
    Column {
        Text(text = "모임의 이름은 무엇인가요?")
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "다음으로")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewDes() {
    Column {
        Text(text = "모임의 목적은 무엇인가요?")
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "다음으로")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewTag() {
    Column {
        Text(text = "어떤 지출을 포함하나요?")
        TextField(value = "", onValueChange = {})
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width=314.dp, height=77.dp)
        ) {
            Text(text = "지출 추가하기")

        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "다음으로")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewDest() {
    Column {
        Text(text = "한 달에 얼마만 쓰고 싶나요?")
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "다음으로")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewPeople() {
    Column {
        Text(text = "누구와 함께 아끼고 싶나요?")
        TextField(value = "", onValueChange = {})
        Button(onClick = { /*TODO*/ }) {
            Text(text = "다음으로")
        }
    }
}