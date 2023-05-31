package com.example.vkr_new_disign.activities.socialNetActivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkr_new_disign.networkBlock.Student

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import java.io.Serializable

class MainMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

//                    var student = intent.getSerializableExtra("student" , Class<Student>()) as Serializable?

                }
            }
        }
    }
}


@Composable
fun UserInfo(student: Student) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "First Name: ${student.firstName}")
        Text(text = "Last Name: $${student.lastname}")
    }
}