package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkr_new_disign.networkBlock.addNewChallange

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.material.OutlinedTextField as OutlinedTextField

class AddIngChallangesActivity : ComponentActivity() {
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
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        var expanded by remember { mutableStateOf(false) }
                        var challangeName by remember { mutableStateOf("") }
                        var challangeTarget by remember { mutableStateOf(0) }
                        var challangeType by remember { mutableStateOf("") }

                        OutlinedTextField(
                            value = challangeName,
                            onValueChange = { challangeName = it },
                            label = { Text("Challange Name") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = challangeTarget.toString(),
                            onValueChange = { challangeTarget = it.toIntOrNull() ?: 0 },
                            label = { Text("Challange Target (Numeric)") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column {
                            Text("challangeType:")
                            Box(
                                modifier = Modifier.clickable(onClick = { expanded = true })
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(challangeType)
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(onClick = { challangeType = "A" }) {
                                        Text("A")
                                    }
                                    DropdownMenuItem(onClick = { challangeType = "T" }) {
                                        Text("T")
                                    }
                                    DropdownMenuItem(onClick = { challangeType = "D" }) {
                                        Text("D")
                                    }
                                }
                            }
                        }




                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { createNewChallenge(challangeName, challangeType, challangeTarget.toString() )
                                var intent = Intent(this@AddIngChallangesActivity, AllChallengesActivity::class.java)
                                finish()
                                startActivity(intent)},
                            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                        ) {
                            Text("Create New Challange")
                        }
                    }
                }
            }
        }
    }
}

private fun createNewChallenge(name: String, type: String, target: String) {
    GlobalScope.launch {
        var body = """{"challangeName":"${name}","challangeType":"${type}","challangeTarget": ${target}}"""
        addNewChallange(body)
    }

}

@Composable
fun Greeting5(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    VKR_new_disignTheme {
        Greeting5("Android")
    }
}