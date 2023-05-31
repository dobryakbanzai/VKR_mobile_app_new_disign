package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.R
import com.example.vkr_new_disign.networkBlock.Teacher
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

var vteacher = mutableStateOf(Teacher())

class AccountTeacherPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var tteacher = intent.getStringExtra("teacher")
                    if (tteacher != null){
                        val json = Json { ignoreUnknownKeys = true }
                        vteacher.value = json.decodeFromString<Teacher>(tteacher)
                    }


                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()


                    Scaffold(

                        scaffoldState = scaffoldState,
                        drawerContent={

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {

                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colors.background)
                                    .weight(9f)
                                )

                                {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text("Моя страница",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        if (vteacher != null){
                                                            println(vteacher.value.firstName)
                                                        }
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Все ученики",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountTeacherPage, AllStudentsActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Все учителя",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountTeacherPage, AllTeacherActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Все текстовые задачи",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountTeacherPage, AllZadsActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                    }


                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Red)
                                        .weight(1f)
                                )
                                {
                                    Text("ВЫХОД",
                                        fontSize = 28.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .clickable(
                                                onClick = {
                                                    val prefs = getSharedPreferences(
                                                        "my_prefs",
                                                        MODE_PRIVATE
                                                    )
                                                    val editor = prefs.edit()
                                                    editor.putString("jwt_token", "")
                                                    editor.apply()
                                                    val intent = Intent(
                                                        this@AccountTeacherPage,
                                                        MainActivity::class.java
                                                    )
                                                    intent.flags =
                                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                                    startActivity(intent)
                                                }
                                            )
                                            .fillMaxSize()
                                    )
                                }

                            }

//




                        }
                    ){
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {

                            TopAppBar {
                                IconButton(onClick = {
                                    scope.launch{ scaffoldState.drawerState.open() }
                                }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Меню")
                                }
                                Text("MATH.ESE", fontSize = 22.sp)

                            }
                            if (student != null){
                                Column (
                                    verticalArrangement = Arrangement.SpaceAround,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(16.dp))

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Box(
                                            modifier = Modifier.weight(8f)
                                        ) {
                                            val painter: Painter = painterResource(id = R.drawable.teacher)
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Image(
                                                    painter = painter,
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .width(100.dp)
                                                        .height(100.dp)
                                                        .clip(CircleShape)
                                                        .background(Color.White),
                                                    alignment = Alignment.TopStart,

                                                    )
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Column() {
                                                    Text(text = vteacher.value.firstName + " ", style = MaterialTheme.typography.h1)
                                                    Text(text = vteacher.value.lastname, style = MaterialTheme.typography.h1)
                                                }
                                            }
                                        }

                                        Icon(modifier = Modifier
                                            .weight(2f)
                                            .clickable(onClick = { println("ssss") }), imageVector = Icons.Default.AccountBox, contentDescription = "sss")

                                    }
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Box(
                                            modifier = Modifier.weight(8f)
                                        ) {
                                            val painter: Painter = painterResource(id = R.drawable.teacher)
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                Column() {
                                                    Text(text = "EXPIRIENCE", style = MaterialTheme.typography.body1)
                                                    Text(text = vteacher.value.experience.toString(), style = MaterialTheme.typography.body2)
                                                }
                                            }
                                        }

                                    }


//
                                }
                            }
                        }
//
                    }
                    

                }
            }
        }
    }

    override fun onBackPressed() {

    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    VKR_new_disignTheme {
        Greeting2("Android")
    }
}