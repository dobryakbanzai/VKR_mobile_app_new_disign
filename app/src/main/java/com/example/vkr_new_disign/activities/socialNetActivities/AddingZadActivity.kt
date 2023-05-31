package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.vkr_new_disign.activities.mathActivities.zapstud_ans
import com.example.vkr_new_disign.networkBlock.ZadCSV
import com.example.vkr_new_disign.networkBlock.addNewZad
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var adzadtext = mutableStateOf("")
var adzadansw = mutableStateOf("")

class AddingZadActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyTabbedScreen()

                }
            }
        }
    }



}

@Composable
fun Greeting7(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    VKR_new_disignTheme {
        Greeting7("Android")
    }
}

@Composable
fun MyTabbedScreen() {
    // List of tab titles
    val tabs = listOf("Ручной ввод", "Ввод из файла csv")

    // Current tab index
    var currentTab by remember { mutableStateOf(0) }

    Column {
        // Tab row with titles
        TabRow(selectedTabIndex = currentTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = currentTab == index,
                    onClick = { currentTab = index }
                )
            }
        }

        // Tab content
        when (currentTab) {
            0 -> picBtn()
            1 -> Text("Пока в разработке")
        }
    }
}

@Composable
private fun picBtn(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = adzadtext.value,
            onValueChange = { adzadtext.value = it },
            label = { Text("Text") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = adzadansw.value,
            onValueChange = { adzadansw.value = it },
            label = { Text("Answer") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                GlobalScope.launch {
                    addNewZad(""" {
    "taskText": "${adzadtext.value}",
    "taskAnswer": "${adzadansw.value}"
} """)
                    adzadtext.value = ""
                    adzadansw.value = ""

                }

            }
        )
        {
            Text(text = "ADD TASK")
        }


    }
}


