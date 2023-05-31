package com.example.vkr_new_disign.activities.loadActivities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.vkr_new_disign.activities.mathActivities.DexTesterActivity
import com.example.vkr_new_disign.activities.socialNetActivities.AllChallengesActivity
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme

import com.example.vkr_new_disign.activities.socialNetActivities.MainActivity
import com.example.vkr_new_disign.networkBlock.applyNewChallange
import com.example.vkr_new_disign.networkBlock.getMyId
import com.example.vkr_new_disign.networkBlock.getToken
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class demoLoadActivity : ComponentActivity() {
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
                }
                GlobalScope.launch(Dispatchers.Main) {
                    // Вызываем метод get() из httpClient внутри suspend функции
                    var token = intent.getStringExtra("token")
                    var body = intent.getStringExtra("body")

                    var id = getMyId(token!!)

                    val response = applyNewChallange(id, body!!)
                    // Обрабатываем ответ от сервера
                    handleResponse()
                }
            }
        }
    }



    private fun handleResponse() {
        // Обрабатываем ответ от сервера
        val intent = Intent(this, AllChallengesActivity::class.java)
        finish()
        // Запускаем activity после получения ответа от сервера
        startActivity(intent)
    }
}