package com.example.vkr_new_disign.activities.loadActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkr_new_disign.activities.socialNetActivities.LoginActivity
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import java.util.*

class Main_Login_Laoding : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoadingIndicator()


//                    Button(
//                        onClick = {
//
//                        }
//                    ) {
//
//
//                    }

                    val timer = Timer()
                    timer.schedule(object : TimerTask() {
                        override fun run() {

                            val intent = Intent(this@Main_Login_Laoding, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // закрываем активность

                        }
                    }, 1000) // 10 секунд

                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VKR_new_disignTheme {
        Greeting2("Android")
    }
}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp)
            .padding(16.dp),
        color = MaterialTheme.colors.primary
    )
}