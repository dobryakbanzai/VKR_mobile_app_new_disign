package com.example.vkr_new_disign.activities.mathActivities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.mathBlock.Gen
import com.example.vkr_new_disign.mathBlock.expression
import com.example.vkr_new_disign.networkBlock.addAryphProg
import com.example.vkr_new_disign.networkBlock.addDerProg
import com.example.vkr_new_disign.networkBlock.getDeriv

import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var appproblem = mutableStateOf("")
var appstud_ans = mutableStateOf("")
var token = mutableStateOf("")

class AryphAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        token.value = sharedPreferences.getString("jwt_token", null).toString()
        do{
            appproblem.value = Gen(lengh = 10)
        }while (appproblem.value.length < 3)

        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CalcApp()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcApp(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.secondaryVariant

            ) {
                Text(text = appproblem.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
            }
            Spacer(modifier = Modifier.width(15.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .weight(2f)
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                elevation = 8.dp,
                backgroundColor = MaterialTheme.colors.secondaryVariant

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = appstud_ans.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }

        // Блок с кнопками
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ){
                btnBlockApp()
            }
        }

    }
}

// Блок с кнопками
@Composable
fun btnBlockApp(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        btnRowApp(f_b = "1", s_b = "2", t_b = "3")
        btnRowApp(f_b = "4", s_b = "5", t_b = "6")
        btnRowApp(f_b = "7", s_b = "8", t_b = "9")
        btnRowApp(f_b = "←", s_b = "0", t_b = "\uD83D\uDC4C")
        Row(

            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            btnNewApp(name = "-", width = 200.dp)
        }

    }

}

// Строка кнопок
@Composable
fun btnRowApp(f_b: String, s_b: String, t_b: String){
    Row(

        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        btnNewApp(name = f_b)
        btnNewApp(name = s_b)
        btnNewApp(name = t_b)
    }
}

// Отдельная кнопка
@Composable
fun btnNewApp(name: String, width: Dp = 100.dp){

    val mContext = LocalContext.current
    var color: Color
    if(name == "\uD83D\uDC4C" || name == "←"){
        color = MaterialTheme.colors.secondary
    }else{
        color = MaterialTheme.colors.primary
    }

    Button(
        onClick = {
            when (name) {
                "←" -> {
                    appstud_ans.value = appstud_ans.value.dropLast(1)
                }
                "\uD83D\uDC4C" -> {
                    if (appstud_ans.value == expression(s = appproblem.value, i = 0).a.toString()) {
                        mToast("Yeah!!!", mContext)

                        appstud_ans.value = ""

                        appproblem.value = Gen(lengh = 10)

                        GlobalScope.launch(Dispatchers.Main) {
                            addAryphProg(token.value)
                        }



                    } else {
                        mToast("NO!!!", mContext)
                    }
                }
                else -> {
                    appstud_ans.value = appstud_ans.value + name
                }
            }
        },

        Modifier
            .width(width),
        border = BorderStroke(5.dp, color),
        shape = RoundedCornerShape(50),

//        colors = ButtonDefaults.outlinedButtonColors(contentColor = color)
    ) {
        Text(text = name, fontSize = 30.sp, color = MaterialTheme.colors.primaryVariant, style = MaterialTheme.typography.button)
    }
}

private fun mToast(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}