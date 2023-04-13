package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.activities.loadActivities.Main_Login_Laoding
import com.example.vkr_new_disign.activities.mathActivities.AryphTesterActivity
import com.example.vkr_new_disign.activities.mathActivities.DexTesterActivity
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        textPrint(text = "MATH.ESE", size = 32.sp)
                        Button(
                            onClick = {
                                val intent = Intent(this@MainActivity, DexTesterActivity::class.java)
                                startActivity(intent)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(72.dp)
                        ) {
                            textPrint(text = "dx/x=?", size = 32.sp, style = MaterialTheme.typography.button)
                        }

                        Button(
                            onClick = {
                                val intent = Intent(this@MainActivity, AryphTesterActivity::class.java)
                                startActivity(intent)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(72.dp)
                        ) {
                            textPrint(text = "a+b*c-d=?", size = 32.sp, style = MaterialTheme.typography.button)
                        }

                        TextButton(
                            onClick = {
                                val intent = Intent(this@MainActivity, Main_Login_Laoding::class.java)
                                startActivity(intent)
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .height(72.dp)
                        ) {
                            textPrint(text = "Login", style = MaterialTheme.typography.button)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", style = MaterialTheme.typography.h1, color = Color.Black)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VKR_new_disignTheme {
        Greeting("Android")
    }
}

