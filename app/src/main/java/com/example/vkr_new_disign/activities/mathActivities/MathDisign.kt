package com.example.vkr_new_disign.activities.mathActivities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.ktor.websocket.*
import java.security.spec.KeySpec


@Composable
fun MainCalc(problem: String = "", answer: String = "", buttons: Array<Array<BtnKey>> = arrayOf(arrayOf(BtnKey("", 0.dp))), daFlag: Boolean = true){

    PrAnBox(problem)

    PrAnBox(answer)

    if(daFlag){

        KeysBox(buttons)

    }else{

        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(2) {
                // replace these placeholders with your own composable elements
                Box(
                    Modifier
                        .background(Color.Gray)
                        .size(120.dp)
                ){

                }
                Box(
                    Modifier
                        .background(Color.Gray)
                        .size(120.dp)
                ){

                }

            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PrAnBox(string: String = ""){

}

@Preview(showBackground = true)
@Composable
fun KeysBox(buttonsBox: Array<Array<BtnKey>> = arrayOf(arrayOf(BtnKey("", 0.dp)))){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (brow in buttonsBox){
            KeysRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeysRow(buttonsRow: Array<BtnKey> = arrayOf(BtnKey("", 0.dp))){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){

        for (btn in buttonsRow){
            KeyMake(btn)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun KeyMake(btnKey: BtnKey = BtnKey("a", 10.dp)){

    Button(
        modifier = Modifier
            .width(btnKey.width),

        onClick = {

        }

    ) {
        Text(text = btnKey.value)
    }

}

data class BtnKey(val value: String, val width: Dp)
