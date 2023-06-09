package com.example.vkr_new_disign.activities.mathActivities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkr_new_disign.activities.socialNetActivities.LoginActivity
import com.example.vkr_new_disign.mathBlock.E
import com.example.vkr_new_disign.mathBlock.Gen
import com.example.vkr_new_disign.mathBlock.Gend
import com.example.vkr_new_disign.mathBlock.expression
import com.example.vkr_new_disign.networkBlock.getDeriv
import com.example.vkr_new_disign.networkBlock.getJWT
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


var dproblem = mutableStateOf("")
var dstud_ans = mutableStateOf("")
var string = mutableStateOf<String>("")
var btn = mutableStateOf( true)

class DexTesterActivity : ComponentActivity() {
//    var j = mutableStateOf<JSONObject>(JSONObject("{}"))

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        var prob = ""

        do {
            prob = Gend(lengh = 8)
        }while (prob.length < 3 || "x" !in prob || ("sin" !in prob && "cos" !in prob))
        dproblem.value = prob
        super.onCreate(savedInstanceState)
        setContent {
            VKR_new_disignTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GlobalScope.launch(Dispatchers.Main) {
                        var response = ""
                        response = getDeriv(dproblem.value)
                        string.value = response.lowercase()
                        println("aaa ${string.value}")
                    }
                    CalcDex()


//                    Text(text = string.value)
//                    problem.value = string.value

                }

            }
        }
    }


//    override fun onBackPressed() {
//        // Создаем Intent для перехода на другую activity и закрытия всех предыдущих
//        val intent = Intent(this, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//    }
}


@Preview(showBackground = true)
@Composable
fun CalcDex(){

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
                Text(text = dproblem.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
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
                    Text(text = dstud_ans.value, fontSize = 20.sp, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center, color = MaterialTheme.colors.primaryVariant, style =MaterialTheme.typography.button)
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


                if (btn.value){
                    BtnBlock()
                }else{
                    BtnBlock1()
                }

            }
        }

    }
}




// Блок с кнопками
@Composable
fun BtnBlock(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BtnRow(arrayOf("1", "2", "3"))
        BtnRow(arrayOf("4", "5", "6"))
        BtnRow(arrayOf("7", "8", "9"))
        BtnRow(arrayOf("←", "0", "\uD83D\uDC4C"))
        Button(onClick = { btn.value = !btn.value }) {
            if (btn.value){
                Text(text = "SinCos...")
            }else{
                Text(text = "12345")
            }
        }
    }

}

@Composable
fun BtnBlock1(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BtnRow(arrayOf("+", "-", "("))
        BtnRow(arrayOf("*", "/", ")"))
        BtnRow(arrayOf( "^", "sin(", "cos("))
        BtnRow(arrayOf( "←", "x", "\uD83D\uDC4C"))

        Button(onClick = { btn.value = !btn.value }) {
            if (btn.value){
                Text(text = "SinCos...")
            }else{
                Text(text = "12345")
            }
        }
    }

}

// Строка кнопок
@Composable
fun BtnRow(arr: Array<String>){
    Row(

        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        for(e in arr){
            BtnNew(name = e)
        }
    }
}

// Отдельная кнопка
@Composable
fun BtnNew(name: String, width: Dp = 100.dp){
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
                    dstud_ans.value = dstud_ans.value.dropLast(1)
                }
                "\uD83D\uDC4C" -> {
                    GlobalScope.launch(Dispatchers.Main) {
                        var response = ""
                        response = getDeriv(dproblem.value)
                        string.value = response.lowercase()
                        println("aaa ${string.value}")
                    }



                    if (dstud_ans.value == string.value){



                        mToast("Yeah!!!", mContext)

                        dstud_ans.value = ""

                        var prob = ""

                        do {
                            prob = Gend(lengh = 8)
                        }while (prob.length < 3 || "x" !in prob || ("sin" !in prob && "cos" !in prob))
                        dproblem.value = prob


                    } else{
                        mToast("NO!!!", mContext)
                    }
                }
                else -> {
                    dstud_ans.value = dstud_ans.value + name
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
