package com.example.vkr_new_disign.activities.mathActivities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.activities.socialNetActivities.mychallanges
import com.example.vkr_new_disign.networkBlock.Challange
import com.example.vkr_new_disign.networkBlock.Zad
import com.example.vkr_new_disign.networkBlock.addZadProg
import com.example.vkr_new_disign.networkBlock.getAllZads

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

var zapproblemlist = mutableListOf<Zad>()
var zapproblem = mutableStateOf(Zad())
var zapstud_ans = mutableStateOf("")
var ztoken = mutableStateOf("")

class ZadsAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        ztoken.value = sharedPreferences.getString("jwt_token", null).toString()
        GlobalScope.launch {
            var resp = getAllZads()
            val json = Json { ignoreUnknownKeys = true }
            zapproblemlist = json.decodeFromString<Array<Zad>>(resp).toMutableList().shuffled() as MutableList<Zad>
            zapproblem.value = zapproblemlist.get(0)
        }
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ZadApp()
                }
            }
        }
    }
}

@Composable
fun ZadApp(){
    val mContext = LocalContext.current
    Spacer(modifier = Modifier.width(15.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.secondaryVariant

            ) {
                Text(text = zapproblem.value.taskText, fontSize = 20.sp, modifier = Modifier.padding(20.dp).verticalScroll(ScrollState(0)), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.secondaryVariant

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextField(
                        value = zapstud_ans.value,
                        onValueChange = { zapstud_ans.value = it },
                        label = { Text("Answer") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }

        // Блок с кнопками
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ){
                Button(
                    onClick = {
                        if(zapproblem.value.taskAnswer == zapstud_ans.value){
                            mToast("YEAH!!!", mContext)

                            GlobalScope.launch {
                                addZadProg(ztoken.value)
                            }
                            zapproblemlist.removeAt(0)
                            zapproblem.value = zapproblemlist.get(0)
                            zapstud_ans.value = ""
                        }else{
                            mToast("NO!!!", mContext)
                        }
                    }
                ){
                    Text(text = "CHECK ANSWER")
                }
            }
        }

    }
}
private fun mToast(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

