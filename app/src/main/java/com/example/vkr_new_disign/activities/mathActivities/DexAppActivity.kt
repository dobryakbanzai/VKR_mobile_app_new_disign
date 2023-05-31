package com.example.vkr_new_disign.activities.mathActivities

import android.annotation.SuppressLint
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
import com.example.vkr_new_disign.mathBlock.Gend
import com.example.vkr_new_disign.networkBlock.Token
import com.example.vkr_new_disign.networkBlock.addAryphProg
import com.example.vkr_new_disign.networkBlock.addDerProg
import com.example.vkr_new_disign.networkBlock.getDeriv
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.async

var appdproblem = mutableStateOf("")
var appdstud_ans = mutableStateOf("")
var appstring = mutableStateOf<String>("")
var appbtn = mutableStateOf( true)
var dtoken = mutableStateOf("")
class DexAppActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        var appprob = ""

        do {
            appprob = Gend(lengh = 8)
        }while (appprob.length < 3 || "x" !in appprob || ("sin" !in appprob && "cos" !in appprob))
        appdproblem.value = appprob

        val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        dtoken.value = sharedPreferences.getString("jwt_token", null).toString()

        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GlobalScope.launch(Dispatchers.Main) {
                        var response = ""
                        response = getDeriv(appdproblem.value)
                        appstring.value = response.lowercase()
                        println("aaa ${appstring.value}")
                    }
                    AppCalcDex()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppCalcDex(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {
//        Spacer(modifier = Modifier.width(15.dp))

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
                Text(text = appdproblem.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
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
                    Text(text = appdstud_ans.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
        }



//        Spacer(modifier = Modifier.width(15.dp))

//        задача
//        BoxMaker(text = dproblem.value, modifier = Modifier
//            .weight(1f)
//            .border(1.dp, Color.Black))
//        BoxMaker(text = string.value, modifier = Modifier.weight(1f).border(2.dp, Color.Black),)

//        ответ пользователя
//        BoxMaker(text = dstud_ans.value, modifier = Modifier
//            .weight(2f)
//            .border(1.dp, Color.Magenta))

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


                if (appbtn.value){
                    AppBtnBlock()
                }else{
                    AppBtnBlock1()
                }

            }
        }

    }
}




// Блок с кнопками
@Composable
fun AppBtnBlock(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBtnRow(arrayOf("1", "2", "3"))
        AppBtnRow(arrayOf("4", "5", "6"))
        AppBtnRow(arrayOf("7", "8", "9"))
        AppBtnRow(arrayOf("←", "0", "\uD83D\uDC4C"))
        Button(onClick = { appbtn.value = !appbtn.value }) {
            if (appbtn.value){
                Text(text = "SinCos...")
            }else{
                Text(text = "12345")
            }
        }
    }

}

@Composable
fun AppBtnBlock1(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppBtnRow(arrayOf("+", "-", "("))
        AppBtnRow(arrayOf("*", "/", ")"))
        AppBtnRow(arrayOf( "^", "sin(", "cos("))
        AppBtnRow(arrayOf( "←", "x", "\uD83D\uDC4C"))

        Button(onClick = { appbtn.value = !appbtn.value }) {
            if (appbtn.value){
                Text(text = "SinCos...")
            }else{
                Text(text = "12345")
            }
        }
    }

}

// Строка кнопок
@Composable
fun AppBtnRow(arr: Array<String>){
    Row(

        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        for(e in arr){
            AppBtnNew(name = e)
        }
    }
}

// Отдельная кнопка
@Composable
fun AppBtnNew(name: String, width: Dp = 100.dp){
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
                    appdstud_ans.value = appdstud_ans.value.dropLast(1)
                }
                "\uD83D\uDC4C" -> {

                    GlobalScope.launch(Dispatchers.Main) {

                        var response = getDeriv(appdproblem.value)
                        appstring.value = response.lowercase()

                        if (appdstud_ans.value == appstring.value){
                            addDerProg(dtoken.value)
                        }


                    }






                    if (appdstud_ans.value == appstring.value){


                        GlobalScope.launch(Dispatchers.Main) {
                            addDerProg(dtoken.value)
                        }



                        mToast("Yeah!!!", mContext)

                        appdstud_ans.value = ""

                        var prob = ""

                        do {
                            prob = Gend(lengh = 8)
                        }while (prob.length < 3 || "x" !in prob || ("sin" !in prob && "cos" !in prob))
                        appdproblem.value = prob



//

                    } else{
                        mToast("NO!!!", mContext)
                    }
                }
                else -> {
                    appdstud_ans.value = appdstud_ans.value + name
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

private suspend fun adp(token: Token){
    GlobalScope.launch(Dispatchers.Main) {
        println("GS2")
        addDerProg(dtoken.value)
    }
}

private fun mToast(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
