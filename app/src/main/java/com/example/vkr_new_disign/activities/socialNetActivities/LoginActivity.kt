package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.vkr_new_disign.activities.loadActivities.loadingJWT
import com.example.vkr_new_disign.cryptoBlock.byteArrToString
import com.example.vkr_new_disign.cryptoBlock.encryptRSA
import com.example.vkr_new_disign.cryptoBlock.stringToPublicKey
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import java.security.PublicKey
import kotlin.math.log

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val token = intent.getStringExtra("token")
        val pk = stringToPublicKey(token.toString())

        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val email = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(40.dp))
                        }

                        textPrint(text = "MATH.ESE", style = MaterialTheme.typography.h1)

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = email.value,
                            onValueChange = { email.value = it },
                            label = { Text("Login") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Email
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            label = { Text("Password") },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Password
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {

                                      enterToSystem(email.value, password.value, pk)

                            },
                            modifier = Modifier.fillMaxWidth()
                        ){
                            textPrint(text = "Login", style = MaterialTheme.typography.button)
                        }

//        Spacer(modifier = Modifier.height(40.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val isDarkTheme = isSystemInDarkTheme()

                            TextButton(
                                onClick =  {
                                    goToRegistr()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                textPrint(text = "Register", style = MaterialTheme.typography.h2)
                            }
                        }
                    }

                }
            }
        }
    }

    private fun goToRegistr(){
        val intent = Intent(this, RegistrationPage::class.java)
        startActivity(intent)
    }

    private fun enterToSystem(login: String, pass: String, pk: PublicKey){
        var loginB = byteArrToString(
            encryptRSA(pk, login)
        )
        println("log $loginB")

        var passB = byteArrToString(
            encryptRSA(pk, pass)
        )
        println("pas $passB")

        val intent = Intent(this, loadingJWT::class.java)
        intent.putExtra("login", loginB)
        intent.putExtra("pass", passB)

        // Запускаем activity после получения ответа от сервера
        startActivity(intent)
    }

    override fun onBackPressed() {
        // Создаем Intent для перехода на другую activity и закрытия всех предыдущих
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreen() {

}

@Composable
fun textPrint(text: String, style: TextStyle = MaterialTheme.typography.h1, size: TextUnit = 16.sp){
    Text(text = text, color = MaterialTheme.colors.primaryVariant, style = style, fontSize = size)
}
