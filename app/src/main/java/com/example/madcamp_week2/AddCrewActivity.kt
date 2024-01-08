@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.components.assetsgroupPost
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import plainTextStyle

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

// navController: NavHostController
// navController.popBackStack()

var cardData = mutableListOf<String>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewName(navController: NavHostController) {
    cardData = remember { mutableListOf() }
    var crewName by remember { mutableStateOf("") }

    Column (
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Text(
            text = "모임의 이름은 무엇인가요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        TextField(
            value = crewName,
            onValueChange = { crewName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navController.navigate("CrewDesAdd/$crewName") },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewDes(navController: NavHostController, crewName: String) {
    var crewDes by remember { mutableStateOf("") }
    var assetsgroupInformationState by remember { mutableStateOf(false)}
    Column (
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Text(
            text = "모임의 목적은 무엇인가요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        TextField(
            value = crewDes,
            onValueChange = { crewDes = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { assetsgroupInformationState = true },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

    if(assetsgroupInformationState){
        assetsgroupPost(assetsgroupInformation(assetsgroupname = crewName, assetsgroupgoal = crewDes), navController)
        assetsgroupInformationState = false
    }
}

var TagList = mutableListOf<String>()
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CrewTag(navController: NavHostController) {

    var buttonList by remember { mutableStateOf(listOf<String>()) }
    var personalTag by remember { mutableStateOf("") }

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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Text(
            text = "어떤 지출을 포함하나요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
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
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField (
            value = personalTag,
            placeholder = { Text(text = "사용자 정의 지출 태그 입력...") },
            onValueChange = { personalTag = it},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    buttonList = buttonList + "#$personalTag"
                    TagList += "$personalTag"
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
        )
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
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    navController.navigate("CrewTarAdd")
                    cardData.add(buttonList.toString())
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewTar(navController: NavHostController) {
    var crewDest by remember { mutableStateOf("") }
    Column (
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Text(
            text = "한 달에 얼마만 쓰고 싶나요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        Column {
            TextField(
                value = crewDest,
                onValueChange = { crewDest = it },
                modifier = Modifier
                    .padding(end = 10.dp),
                placeholder = { Text(text = "금액 입력") },
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    navController.navigate("CrewPeopleAdd")
                    cardData.add(crewDest)
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

// navController: NavHostController
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

@Composable
fun CrewPeople(navController: NavHostController) {
    var crewPeople by remember { mutableStateOf(listOf("공영재","강민구","김수환", "김가연", "김윤서", "정민서")) }
    var searchPeople by remember { mutableStateOf("") }
    var filteredPeople by remember { mutableStateOf(crewPeople) }
    var selectedPeople by remember { mutableStateOf(listOf<String>()) }
    Column (
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
                Text(
                    text = "뒤로가기",
                    color = Color.Black
                )
            }
        }
        Text(
            text = "누구와 함께 아끼고 싶나요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
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
                    items(selectedPeople) { peopleLabel ->
                        Button(
                            onClick = { selectedPeople = selectedPeople.toMutableList().apply {
                                remove(peopleLabel)
                            } },
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Box (
                                modifier = Modifier
                                    .padding(4.dp)
                            ) {
                                Text(text = peopleLabel)
                            }
                        }
                    }
                }
            }
        }
        val keyboardController = LocalSoftwareKeyboardController.current
        Row(
            modifier = Modifier
            .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = searchPeople,
                onValueChange = {
                    searchPeople = it
                    filteredPeople = performSearch(it, crewPeople)
                },
                label = { Text("이름을 입력하세요") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp),
        ) {
            LazyColumn {
                items(filteredPeople) { person ->
                    Button(
                        onClick = { selectedPeople = selectedPeople + "$person" },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "$person")
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
            Button(
                onClick = {
                    navController.navigate("Home")
                    cardData.add(selectedPeople.toString())
                    cardDataList.add(cardData.toString())
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

fun performSearch(query: String, targetData: List<String>): List<String> {
    val filteredData = targetData
    return filteredData.filter { it.contains(query, ignoreCase = true) }
}