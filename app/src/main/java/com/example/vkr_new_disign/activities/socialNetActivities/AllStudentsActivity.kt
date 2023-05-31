package com.example.vkr_new_disign.activities.socialNetActivities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.networkBlock.Student
import com.example.vkr_new_disign.networkBlock.getAllStud
import com.example.vkr_new_disign.networkBlock.getMyselfStud
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

data class CardData(val firstName: String, val lastName: String, val aryphProg: String, val derProg: String, val taskProg: String)

val cardList = mutableStateListOf<CardData>()

class AllStudentsActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    GlobalScope.launch(Dispatchers.Main){
                        var response = getAllStud()
                        val json = Json { ignoreUnknownKeys = true }
                        var arrResp = json.decodeFromString<Array<Student>>(response)
                        var arrRespSorted = arrResp.sortedBy { p -> (0 - (p.derProg + p.aryProg + p.tasksProg)) }

                        for (s in arrRespSorted){
                            cardList.add(
                                CardData(
                                    firstName = s.firstName,
                                    lastName = s.lastname,
                                    aryphProg = s.aryProg.toString(),
                                    derProg = s.derProg.toString(),
                                    taskProg = s.tasksProg.toString()
                                )
                            )
                        }
                    }

                    LCElement()
                }
            }
        }
    }

    override fun onBackPressed() {
        cardList.clear()
        finish()
    }
}

@Composable
fun LCElement(){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(cardList) { cardData ->
            StudentCard(cardData.firstName, cardData.lastName, cardData.aryphProg, cardData.derProg, cardData.taskProg)
        }
    }
}


@Composable
fun StudentCard(firstName: String, lastName: String, aryphProg: String, derProg: String, taskProg: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "$firstName $lastName", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Aryphprog: $aryphProg")
            Text(text = "Derprog: $derProg")
            Text(text = "Taskprog: $taskProg")
        }
    }
}