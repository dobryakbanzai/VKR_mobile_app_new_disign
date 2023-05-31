package com.example.vkr_new_disign.activities.loadActivities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkr_new_disign.activities.socialNetActivities.AccountPage
import com.example.vkr_new_disign.activities.socialNetActivities.AccountTeacherPage
import com.example.vkr_new_disign.activities.socialNetActivities.LoginActivity
import com.example.vkr_new_disign.activities.socialNetActivities.MainActivity
import com.example.vkr_new_disign.networkBlock.*
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class loadingJWT : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Spacer(modifier = Modifier
//                        .fillMaxWidth()
//                        .height(20.dp))
//                    LoadingIndicator()
//
                    LoadingIndicator()
                    GlobalScope.launch(Dispatchers.Main) {
                        var login = intent.getStringExtra("login")
                        var pass = intent.getStringExtra("pass")
                        var response = ""
                        // Вызываем метод get() из httpClient внутри suspend функции

                        if (login != null && pass != null){
                            response = getJWT(login, pass)
                        }

                        val prefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

                        val editor = prefs.edit()

                        editor.putString("jwt_token", response)

                        editor.apply()


                        println(response)

                        var role = getMyRole(response)

                        var response2 = ""
                        // Вызываем метод get() из httpClient внутри suspend функции
                        if (response != null){

                            if(role == "student"){
                                response2 = getMyselfStud(response)
                            }else{
                                response2 = getMyselfTeacher(response)
                            }

                        } else{
                            errorResponse()
                        }

                        if("firstname" !in response2){
                            val intent = Intent(this@loadingJWT, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }else{
                            if (role == "student"){
                                handleResponse(response2)
                            }else{
                                handleTeacher(response2)
                            }
                        }
                    }
//

//                    MyApp()
                }
            }
        }
    }
    
    private fun handleResponse(response: String) {
        println("LOAD JWT ENDED")
        // Обрабатываем ответ от сервера
        val intent = Intent(this, AccountPage::class.java)
        intent.putExtra("student", response)
        // Запускаем activity после получения ответа от сервера
        startActivity(intent)
    }

    private fun handleTeacher(response: String){
//        teacher
        val intent = Intent(this, AccountTeacherPage::class.java)
        intent.putExtra("teacher", response)
        startActivity(intent)
    }

    private fun errorResponse(){
        val intent = Intent(this, Main_Login_Loading::class.java)
        startActivity(intent)
    }
}

@Composable
fun MyApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    Scaffold(
        topBar = {
            // top bar content
        },
        drawerContent = {
            // drawer content
        },
        drawerShape = MaterialTheme.shapes.large,
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        drawerScrimColor = Color.Black.copy(alpha = 0.6f),
        drawerGesturesEnabled = true,

    ) { innerPadding ->
        // main app content
    }
}

@Composable
fun SideMenu() {
    LazyColumn {
        item {
            Text(
                text = "Menu item 1",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable(onClick = { /* perform action or navigation */ })
            )
        }
        item {
            Text(
                text = "Menu item 2",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable(onClick = { /* perform action or navigation */ })
            )
        }
        // and so on...
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    VKR_new_disignTheme {
//        Greeting2("Android")
    }
}