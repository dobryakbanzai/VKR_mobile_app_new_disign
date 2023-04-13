package com.example.vkr_new_disign.activities.mathActivities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import com.example.vkr_new_disign.ui.theme.VKR_new_disignTheme

private val showDialog = mutableStateOf(false)
var problem = mutableStateOf("")
var stud_ans = mutableStateOf("")

class AryphTesterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        do{
            problem.value = Gen(lengh = 10)
        }while (problem.value.length < 3)

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
                        Calc()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Calc(){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

    ) {

//        задача
        boxMaker(text = problem.value, modifier = Modifier.weight(1f).border(2.dp, Color.Black),)

//        ответ пользователя
        boxMaker(text = stud_ans.value, modifier = Modifier.weight(2f).border(2.dp, Color.Black),)

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
                btnBlock()
            }
        }

    }
}

@Composable
fun boxMaker(text: String, modifier: Modifier){
    Box(
        modifier,
        contentAlignment = Alignment.Center,

        ){
        Text(text = text, modifier = Modifier.fillMaxWidth(1f) ,  fontSize=22.sp, textAlign = TextAlign.Center)
    }
}

// Блок с кнопками
@Composable
fun btnBlock(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        btnRow(f_b = "1", s_b = "2", t_b = "3")
        btnRow(f_b = "4", s_b = "5", t_b = "6")
        btnRow(f_b = "7", s_b = "8", t_b = "9")
        btnRow(f_b = "←", s_b = "0", t_b = "\uD83D\uDC4C")
        Row(

            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            btnNew(name = "-", width = 200.dp)
        }

    }

}

// Строка кнопок
@Composable
fun btnRow(f_b: String, s_b: String, t_b: String){
    Row(

        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        btnNew(name = f_b)
        btnNew(name = s_b)
        btnNew(name = t_b)
    }
}

// Отдельная кнопка
@Composable
fun btnNew(name: String, width: Dp = 100.dp){
    val mContext = LocalContext.current


    Button(
        onClick = {
            when (name) {
                "←" -> {
                    stud_ans.value = stud_ans.value.dropLast(1)
                }
                "\uD83D\uDC4C" -> {
                    if (stud_ans.value == expression(s = problem.value, i = 0).a.toString()){
                        mToast("Yeah!!!", mContext)

                        stud_ans.value = ""

                        problem.value = Gen(lengh = 10)


                    } else{
                        mToast("NO!!!", mContext)
                    }
                }
                else -> {
                    stud_ans.value = stud_ans.value + name
                }
            }
        },
        Modifier
            .width(width),
        border = BorderStroke(1.dp, Color.Green),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Green)
    ) {
        Text(text = name, fontSize = 30.sp)
    }
}

private fun mToast(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
