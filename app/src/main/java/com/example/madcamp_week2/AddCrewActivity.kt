@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.madcamp_week2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.madcamp_week2.ViewModel.memberViewModel
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupInformation
import com.example.madcamp_week2.serverInterface.classComponents.assetsgroupmemberpair
import com.example.madcamp_week2.serverInterface.classComponents.categoryInformation
import com.example.madcamp_week2.serverInterface.components.GET.getAllMembers
import com.example.madcamp_week2.serverInterface.components.POST.assetsgroupPost
import com.example.madcamp_week2.serverInterface.components.POST.assetsgroupcategorypairPost
import com.example.madcamp_week2.serverInterface.components.POST.assetsgroupmemberpairPost
import com.example.madcamp_week2.serverInterface.components.POST.categoryPost
import com.example.madcamp_week2.ui.theme.Brown40
import com.example.madcamp_week2.ui.theme.BrownGrey40
import com.example.madcamp_week2.ui.theme.PointBackground
import com.example.madcamp_week2.ui.theme.TotalBackgroundColor
import middleTitleTextStyle
import smallPlainTextStyle

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
var crewPeople = mutableStateOf(listOf<String>())

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CrewName(navController: NavHostController, memberViewModel: memberViewModel) {
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
                onClick = {
                    navController.popBackStack()
                          },
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
            text = "모임의 이름은 무엇인가요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = crewName,
            placeholder = { Text(text = "입력...") },
            onValueChange = { crewName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    cardData.add(crewName)
                    navController.navigate("CrewDesAdd")
                }
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    cardData.add(crewName)
                    navController.navigate("CrewDesAdd")
                    },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CrewDes(navController: NavHostController, memberViewModel: memberViewModel) {
    var crewDes by remember { mutableStateOf("") }

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
                onClick = {
                    navController.popBackStack()
                    cardData.removeAt(cardData.size - 1)
                          },
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
            text = "모임을 설명해주세요.",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = crewDes,
            placeholder = { Text(text = "입력...") },
            onValueChange = { crewDes = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    cardData.add(crewDes)
                    navController.navigate("CrewTagAdd")
                }
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    cardData.add(crewDes)
                    navController.navigate("CrewTagAdd")
                          },

                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

}

var TagList = mutableListOf<String>()
var usrTagList = mutableListOf<String>()
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CrewTag(navController: NavHostController, memberViewModel: memberViewModel) {

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
                onClick = {
                    navController.popBackStack()
                    cardData.removeAt(cardData.size - 1)
                          },
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
                    containerColor = Brown40,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(80.dp),
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "선택된 지출 태그",
                        style = smallPlainTextStyle,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(start = 10.dp)
                    )
                }
                LazyRow {
                    items(buttonList) { buttonLabel ->
                        Button(
                            onClick = {
                                buttonList = buttonList.toMutableList().apply {
                                remove(buttonLabel)
                            }
                                      },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrownGrey40
                            ),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            placeholder = { Text(text = "사용자 정의 지출 태그 추가...") },
            onValueChange = { personalTag = it},
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    if (!buttonList.contains("#$personalTag")) {
                        buttonList += "#$personalTag"
                        usrTagList += "#$personalTag"
                        personalTag = ""
                    }
                }
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = PointBackground,
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
                        onClick = {
                            if (!buttonList.contains("#식비"))
                                buttonList += "#식비"
                                  },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#식비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#교통비"))
                                buttonList += "#교통비"
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#교통비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#간식비"))
                                buttonList += "#간식비"
                                  },
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
                        onClick = {
                            if (!buttonList.contains("#꾸밈비"))
                                buttonList += "#꾸밈비"
                                  },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#꾸밈비")
                    }
                    Button(
                        onClick = {
                            if (!buttonList.contains("#여가비"))
                                buttonList += "#여가비"
                                  },
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    ) {
                        Text(text = "#여가비")
                    }
                }
            }
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = PointBackground,
            ),
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(80.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(
                    text = "추가된 사용자 정의 태그",
                    style = smallPlainTextStyle,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(start = 10.dp)
                    )
            }
            LazyRow {
                items(usrTagList) { tagLabel ->
                    Button(
                        onClick = {
                            if (!buttonList.contains("$tagLabel"))
                                buttonList += "$tagLabel"
                        },
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    TagList.addAll(buttonList)
                    cardData.add(buttonList.toString())
                    navController.navigate("CrewTarAdd")
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }
}

//navController: NavHostController
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CrewTar(navController: NavHostController, memberViewModel: memberViewModel) {
    var crewDest by remember { mutableStateOf("") }
    var addGroup by remember { mutableStateOf(false)}
    val context = LocalContext.current
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
                onClick = {
                    navController.popBackStack()
                    cardData.removeAt(cardData.size - 1)
                          },
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
            text = "한 달에 얼마만 쓰고 싶나요?",
            style = middleTitleTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 30.dp)
        )
        Text(
            text = "숫자로 입력해주세요.",
            style = smallPlainTextStyle,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(end = 10.dp)
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = crewDest,
                    onValueChange = { crewDest = it },
                    modifier = Modifier
                        .padding(end = 10.dp),
                    placeholder = { Text(text = "입력...") },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            cardData.add(crewDest)
                            navController.navigate("CrewDesAdd")
                        }
                    )
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
            Button(
                onClick = {
                    if(crewDest.toIntOrNull() == null){
                        Toast.makeText(context, "유효한 값이 아닙니다.", Toast.LENGTH_SHORT)
                    }
                    else {
                        addGroup = true
                        navController.navigate("CrewPeopleAdd")
                        cardData.add(crewDest)
                    } },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

    if (addGroup){
        getAllMembers(crewPeople)
        TagList.forEach{
                tag -> categoryPost(categoryInformation(tag), navController)
        }
        addGroup = false
        assetsgroupPost(assetsgroupInformation("","", assetsgroupname = cardData[0], assetsgroupgoal = cardData[1], emptyList(), 0, 0), navController, memberViewModel = memberViewModel)
    }

}
// navController: NavHostController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CrewPeople(navController: NavHostController, memberViewModel: memberViewModel) {

    var searchPeople by remember { mutableStateOf("") }
    var filteredPeople by remember { mutableStateOf(crewPeople.value) }
    var selectedPeople by remember { mutableStateOf(listOf<String>()) }
    var addDatabaseState by remember { mutableStateOf(false) }
    var awaitState by remember { mutableStateOf(false) }
    var group_id by remember {mutableStateOf("0")}

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
                onClick = {
                    navController.popBackStack()
                    cardData.removeAt(cardData.size - 1)
                          },
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
                    containerColor = Brown40,
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .height(80.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()) {
                    Text(
                        text = "함께할 친구",
                        style = smallPlainTextStyle,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(start = 10.dp)
                    )
                }
                LazyRow {
                    items(selectedPeople) { peopleLabel ->
                        Button(
                            onClick = {
                                selectedPeople = selectedPeople.toMutableList().apply {

                                remove(peopleLabel)
                            }
                                      },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrownGrey40
                            ),
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Box(
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
                    memberViewModel.id.value?.let { id ->
                        filteredPeople = crewPeople.value.filter {
                            it.contains(
                                searchPeople,
                                ignoreCase = true
                            ) && (it != id)
                        }
                    }
                },
                label = { Text("이름 검색...") },
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
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )
        }
        Card(
            colors = CardDefaults.cardColors(
                containerColor = PointBackground,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 30.dp)
                .padding(top = 10.dp),
        ) {
            LazyColumn {
                items(filteredPeople) { person ->

                    memberViewModel.id.value?.let { id ->
                        if(id!=person){
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
                    cardData.add(selectedPeople.toString())
                    cardData.add("0") // 지출한 금액 계산하기 위해 맨 마지막에 추가해주기

                    cardDataList.add(cardData)


                    addDatabaseState = true
                    navController.navigate("Home")
                          },
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(text = "다음으로")
            }
        }
    }

    memberViewModel.group_id.value?. let{
        group_id = it
    }

    if (addDatabaseState) {
        addDatabaseState = false
        selectedPeople.forEach { people ->
            assetsgroupmemberpairPost(navController, memberViewModel, people, 0)
        }
        memberViewModel.id.value?.let {
            assetsgroupmemberpairPost(navController, memberViewModel, it, cardData[3].toInt())
        }
        awaitState = true
    }

    if(awaitState){
        val regex = Regex("#\\w+")
        val tagListRegister = regex.findAll(cardData[2]).map { it.value }.toList()
        tagListRegister.forEach { category -> assetsgroupcategorypairPost(navController, memberViewModel, group_id, category) }
        awaitState = false
    }

}