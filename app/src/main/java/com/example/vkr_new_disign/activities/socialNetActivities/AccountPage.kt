package com.example.vkr_new_disign.activities.socialNetActivities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.vkr_new_disign.networkBlock.Student

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.vkr_new_disign.R
import com.example.vkr_new_disign.activities.mathActivities.AryphAppActivity
import com.example.vkr_new_disign.activities.mathActivities.DexAppActivity
import com.example.vkr_new_disign.activities.mathActivities.ZadsAppActivity
import com.example.vkr_new_disign.activities.mathActivities.token
import com.example.vkr_new_disign.networkBlock.Challange
import com.example.vkr_new_disign.networkBlock.getAllMy
import com.example.vkr_new_disign.networkBlock.getMyselfStud
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


var student = mutableStateOf(Student())
var mychallanges = mutableListOf<Challange>()

class AccountPage : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {

                val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("jwt_token", null).toString()

                GlobalScope.launch {
                    var resp = getAllMy(token)
                    val json = Json { ignoreUnknownKeys = true }
                    mychallanges = json.decodeFromString<Array<Challange>>(resp).toMutableList()

                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var stud = intent.getStringExtra("student")




                    if (stud != null){
                        val json = Json { ignoreUnknownKeys = true }
                        student.value = json.decodeFromString<Student>(stud)
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
                                                        if (student != null){
                                                            println(student.value.firstName)
                                                        }
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Все ученики",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountPage, AllStudentsActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Все учителя",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountPage, AllTeacherActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )


                                        Text("Все вызовы",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        mychallanges.clear()
                                                        finish()
                                                        var intent = Intent(this@AccountPage, AllChallengesActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Арифметика",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountPage, AryphAppActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Производные",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountPage, DexAppActivity::class.java)
                                                        startActivity(intent)
                                                    }
                                                ).fillMaxWidth()
                                        )

                                        Text("Задачи",
                                            fontSize = 28.sp,
                                            modifier = Modifier
                                                .clickable(
                                                    onClick = {
                                                        var intent = Intent(this@AccountPage, ZadsAppActivity::class.java)
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
                                                        Context.MODE_PRIVATE
                                                    )
                                                    val editor = prefs.edit()
                                                    editor.putString("jwt_token", "")
                                                    editor.apply()
                                                    val intent = Intent(
                                                        this@AccountPage,
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
                                            val painter: Painter = painterResource(id = R.drawable.student)
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
                                                    Text(text = student.value.firstName + " ", style = MaterialTheme.typography.h1)
                                                    Text(text = student.value.lastname, style = MaterialTheme.typography.h1)
                                                }
                                            }
                                        }

                                        Icon(modifier = Modifier
                                            .weight(2f)
                                            .clickable(onClick = { println("ssss") }), imageVector = Icons.Default.AccountBox, contentDescription = "sss")

                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {

                                        Box(modifier = Modifier.weight(1f)) {
                                          Column(
                                              verticalArrangement = Arrangement.SpaceEvenly,
                                              horizontalAlignment = Alignment.CenterHorizontally,
                                              modifier = Modifier.fillMaxWidth()
                                          ) {
                                              Text(text = "A", style = MaterialTheme.typography.button)
                                              Text(text = student.value.aryProg.toString(), style = MaterialTheme.typography.button)
                                          }
                                        }

                                        Box(modifier = Modifier.weight(1f)) {
                                          Column(
                                              verticalArrangement = Arrangement.SpaceEvenly,
                                              horizontalAlignment = Alignment.CenterHorizontally,
                                              modifier = Modifier.fillMaxWidth()
                                          ) {
                                              Text(text = "D", textAlign = TextAlign.Center, style = MaterialTheme.typography.button)
                                              Text(text = student.value.derProg.toString(), style = MaterialTheme.typography.button)
                                          }
                                        }

                                        Box(modifier = Modifier.weight(1f)) {
                                          Column(
                                              verticalArrangement = Arrangement.SpaceEvenly,
                                              horizontalAlignment = Alignment.CenterHorizontally,
                                              modifier = Modifier.fillMaxWidth()
                                          ) {
                                              Text(text = "T", style = MaterialTheme.typography.button)
                                              Text(text = student.value.tasksProg.toString(), style = MaterialTheme.typography.button)
                                          }
                                        }

                                    }


                                    
                                    
                                    
                                    Column(
                                        verticalArrangement = Arrangement.Bottom,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "CHALLENGES", style = MaterialTheme.typography.h1, fontSize = 20.sp, color = MaterialTheme.colors.primaryVariant)
                                        MyLazyRow(challanges = mychallanges)
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

    private fun mToast(text: String, context: Context){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null).toString()
        GlobalScope.launch(Dispatchers.Main) {
            var response = getMyselfStud(token)
            var resp = getAllMy(token)

            val json = Json { ignoreUnknownKeys = true }
            student.value = json.decodeFromString<Student>(response)

            mychallanges = json.decodeFromString<Array<Challange>>(resp).toMutableList()
        }

    }

    override fun onRestart() {
        super.onRestart()
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwt_token", null).toString()
        GlobalScope.launch(Dispatchers.Main) {
            var response = getMyselfStud(token)
            var resp = getAllMy(token)

            val json = Json { ignoreUnknownKeys = true }
            mychallanges = json.decodeFromString<Array<Challange>>(resp).toMutableList()
            student.value = json.decodeFromString<Student>(response)
        }

    }

    override fun onBackPressed() {

    }
}


@Composable
fun MyLazyRow(challanges: List<Challange>) {


    var challangesSorted = challanges.sortedBy{ p -> (0 - p.challangeTarget)}

    LazyRow {
        items(challangesSorted.size) { index ->
            val challange = challangesSorted[index]
            var color = MaterialTheme.colors.secondary
            if (challange.challangeType == "A"){
                if(challange.challangeTarget - student.value.aryProg <= 0){
                    color = Color.Gray
                }
            }
            if (challange.challangeType == "D"){
                if(challange.challangeTarget - student.value.derProg <= 0){
                    color = Color.Green
                }
            }
            if (challange.challangeType == "T"){
                if(challange.challangeTarget - student.value.tasksProg <= 0){
                    color = Color.Gray
                }
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .width(200.dp)
                    .height(355.dp),

                backgroundColor = color
            ) {
                Column (
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = " Осталось ", style = MaterialTheme.typography.h1, fontSize = 16.sp)
                        if (challange.challangeType == "A"){
                            Text(text = "${challange.challangeTarget - student.value.aryProg}")
                        }
                        if (challange.challangeType == "D"){
                            Text(text = "${challange.challangeTarget - student.value.derProg}")
                        }
                        if (challange.challangeType == "T"){
                            Text(text = "${challange.challangeTarget - student.value.tasksProg}")
                        }

                    }


                }
            }

        }
    }
}
