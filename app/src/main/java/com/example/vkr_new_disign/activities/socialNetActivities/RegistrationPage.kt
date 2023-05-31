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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkr_new_disign.networkBlock.registrNewStudent
import com.example.vkr_new_disign.networkBlock.registrNewTeacher
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RegistrationPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var expanded by remember { mutableStateOf(false) }
                    var lastName by remember { mutableStateOf("") }
                    var firstName by remember { mutableStateOf("") }
                    var selectedRole by remember { mutableStateOf("") }
                    var className by remember { mutableStateOf("") }
                    var login by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") }
                        )
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = {Text("First Name" )}
                        )

                        Column {
                            Text("Select a role:")
                            Box(
                                modifier = Modifier.clickable(onClick = { expanded = true })
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(selectedRole)
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(onClick = {
                                        selectedRole = "student"
                                        expanded = false
                                    }) {
                                        Text("student")
                                    }
                                    DropdownMenuItem(onClick = {
                                        selectedRole = "teacher"
                                        expanded = false
                                    }) {
                                        Text("teacher")
                                    }
                                }
                            }
                        }


                        OutlinedTextField(
                            value = className,
                            onValueChange = { className = it },
                            label = { Text("Class/Experience")},
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        OutlinedTextField(
                            value = login,
                            onValueChange = { login = it },
                            label = { Text("Login") }
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Button(
                            onClick = {
                                registrationNewPerson(lastName, firstName, selectedRole, className.toInt(), login, password)
                            },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text(text = "Register")
                        }
                    }
                }
            }
        }
    }

    private fun registrationNewPerson(lastname: String, firstname: String, role: String, className: Int, login: String, pass: String){
        if(role == "student"){

            val body =  """{"firstname":"$firstname","lastname":"$lastname","personRole":"$role","edClass":$className,"login":"$login","pass":"$pass"}"""
            GlobalScope.launch(Dispatchers.Main) {
                println(registrNewStudent(body))
            }
        }else{
            val body =  """{"firstname":"$firstname","lastname":"$lastname","personRole":"$role", "experience":"$className", "login":"$login","pass":"$pass"}"""
            GlobalScope.launch(Dispatchers.Main) {
                println(registrNewTeacher(body))
            }
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

@Composable
fun Greeting4(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    VKR_new_disignTheme {
        Greeting4("Android")
    }
}