package com.example.vkr_new_disign.activities.loadActivities

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp

import com.example.vkr_new_disign.activities.socialNetActivities.LoginActivity
import com.example.vkr_new_disign.networkBlock.getToken
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class Main_Login_Loading : ComponentActivity() {
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

                    LoadingIndicator()

                    GlobalScope.launch(Dispatchers.Main) {
                        // Вызываем метод get() из httpClient внутри suspend функции
                        val response = getToken()
                        // Обрабатываем ответ от сервера
                        handleResponse(response)
                    }

                }
            }
        }
    }

    private fun handleResponse(response: String) {
        // Обрабатываем ответ от сервера
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("token", response)
        // Запускаем activity после получения ответа от сервера
        startActivity(intent)
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