package com.example.vkr_new_disign.activities.socialNetActivities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.vkr_new_disign.activities.loadActivities.demoLoadActivity
import com.example.vkr_new_disign.networkBlock.*
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.coroutines.*

var anchallanges = mutableListOf<Challange>()
var isRunning = mutableStateOf(false)
class AllChallengesActivity : ComponentActivity() {

    override fun onBackPressed() {
        // Создаем Intent для перехода на другую activity и закрытия всех предыдущих
        val intent = Intent(this, AccountPage::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    @SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) = runBlocking  {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null).toString()
        GlobalScope.launch {
            var resp = getAnotherChallanges(token)
            val json = Json { ignoreUnknownKeys = true }
            anchallanges = json.decodeFromString<Array<Challange>>(resp).toMutableList()
        }
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {



                    Scaffold(
                        topBar = { TopAppBar(title = { Text("MATH.ESE") }) },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    var intent = Intent(this@AllChallengesActivity, AddIngChallangesActivity::class.java)
                                    finish()
                                    startActivity(intent)
                                }
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        },
                        content = {
                            LazyColumn {
                                items(anchallanges.size) { index ->
                                    val challange = anchallanges[index]
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        elevation = 4.dp,
                                        backgroundColor = MaterialTheme.colors.secondary
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {

                                            Column (
                                                verticalArrangement = Arrangement.SpaceEvenly,
                                                horizontalAlignment = Alignment.Start,
                                                modifier = Modifier

                                            ){
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Text(text = " Название ", style = MaterialTheme.typography.h1, fontSize = 16.sp )
                                                    Text(text = "${challange.challangeName}")
                                                }

                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Text(text = " Цель ", style = MaterialTheme.typography.h1, fontSize = 16.sp)
                                                    Text(text = "${challange.challangeTarget}")
                                                }

                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Text(text = " Тип ", style = MaterialTheme.typography.h1, fontSize = 16.sp)
                                                    Text(text = "${challange.challangeType}")
                                                }


                                            }
//                                            IconWithSuspendFunction(token = token, body = """ "challangeId":"${challange.id}" """)
//
                                            Icon(Icons.Filled.Add, contentDescription = "Add", Modifier.clickable(
                                            onClick = {
//                                                anchallanges.removeAt(index)
                                                var body = challange.id.toString()
//                                                iconBtn(token = token, body = """ "challangeId":"${challange.id}" """)

                                                val intent = Intent(this@AllChallengesActivity, demoLoadActivity::class.java)
                                                intent.putExtra("token", token)
                                                intent.putExtra("body", body)
                                                finish()
                                                startActivity(intent)



//                                                finish()
//                                                startActivity(getIntent())
                                            }
                                            ))

//                                            if (isRunning.value){
//                                                finish()
//                                                startActivity(getIntent())
//                                                isRunning.value = !isRunning.value
//                                            }
                                        }



                                    }
                                }
                            }
//                            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
//                            ) {
//
//                                Text("You have clicked the button ${counter.value} times")
//                            }
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun iconBtn(token: String, body: String){
    LaunchedEffect (true){
        applyNewChallange(token, body)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun IconWithSuspendFunction(token: String, body: String) {

    LaunchedEffect(isRunning) {
        if (isRunning.value) {
            // Run your suspend function here
            applyNewChallange(token, body)
        }
    }
    IconButton(onClick = { isRunning.value = !isRunning.value }) {
        Icon(Icons.Default.Add, contentDescription = "Play")
    }
}
