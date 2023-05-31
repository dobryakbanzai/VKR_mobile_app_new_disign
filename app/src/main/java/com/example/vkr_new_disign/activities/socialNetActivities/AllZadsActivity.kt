package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.activities.loadActivities.demoLoadActivity

import com.example.vkr_new_disign.networkBlock.Zad
import com.example.vkr_new_disign.networkBlock.getAllZads

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

var allzads = mutableListOf<Zad>()
class AllZadsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null).toString()
        GlobalScope.launch {
            var resp = getAllZads()
            val json = Json { ignoreUnknownKeys = true }
            allzads = json.decodeFromString<Array<Zad>>(resp).toMutableList()
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
                                    var intent = Intent(this@AllZadsActivity, AddingZadActivity::class.java)
                                    finish()
                                    startActivity(intent)
                                }
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        },
                        content = {
                            LazyColumn {
                                items(allzads.size) { index ->
                                    val zad = allzads[index]
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
                                                    Text(text = " Условие ", style = MaterialTheme.typography.h1, fontSize = 16.sp )
                                                    Text(text = "${zad.taskText}")
                                                }

                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceEvenly
                                                ) {
                                                    Text(text = " Ответ ", style = MaterialTheme.typography.h1, fontSize = 16.sp)
                                                    Text(text = "${zad.taskAnswer}")
                                                }




                                            }

                                        }

                                    }
                                }
                            }
                        }
                    )

                }
            }
        }
    }
    override fun onBackPressed() {
        allzads.clear()
        finish()
    }
}

@Composable
fun Greeting6(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    VKR_new_disignTheme {
        Greeting6("Android")
    }
}