package com.example.vkr_new_disign.mathBlock

fun Ed(len: Int, lengh: Int): String {
    if (len in 1..lengh){
        val l = (1..10).random()
        if (l == 1){
            return("T1+E")
        }else if(l == 2){
            return("T1-E")
        }else if(l == 3) {
            return ("T1+x")
        }else if(l == 4){
            return ("T1-x")
        }else if(l == 5){
            return ("sin(T1)")
        }else if(l == 6){
            return ("cos(T1)")
        }else if(l == 7){
            return ("sin(x)+T1")
        }else if(l == 8){
            return ("sin(x)-T1")
        }else if(l == 9){
            return ("sin(T1)+E")
        }else if(l == 10){
            return ("cos(T1)-E")
        }
    }else if (len > lengh){
        return ("T1")
    }else if(len == -1){
        val l = (1..10).random()
        if (l == 1){
            return("T1+E")
        }else if(l == 2){
            return("T1-E")
        }else if(l == 3) {
            return ("T1+x")
        }else if(l == 4){
            return ("T1-x")
        }else if(l == 5){
            return ("sin(T1)")
        }else if(l == 6){
            return ("cos(T1)")
        }else if(l == 7){
            return ("sin(x)+T1")
        }else if(l == 8){
            return ("sin(x)-T1")
        }else if(l == 9){
            return ("sin(T1)+E")
        }else if(l == 10){
            return ("cos(T1)-E")
        }
    }
    return ("----")
}

fun T1d(len: Int, lengh: Int):String{
    if (len < lengh){
        val l = (1..2).random()
        if (l == 1){
            return ("T1*T2")
        }else if( l == 2){
            return ("T2")
        }
    }else{
        val l = (1..2).random()
        if (l == 1){
            return ("T1*T2")
        }else if( l == 2){
            return ("T2")
        }

    }
    return ("----")
}

fun T2d(len: Int, lengh: Int):String{
    if (len < lengh){
        val l = (1..2).random()
        if (l == 1){
            return Numsd()
        }else if(l == 2){
            var et3 = Ed(-1, lengh)
            var st3 = "($et3)"
            return (st3)
        }
    }else{
        return Numsd()
    }
    return ("----")
}

fun Numsd():String{
    return (0..9).random().toString()
}

fun Gend(str: String = "E", lengh: Int): String {
    var buf_str = str
    while ((buf_str.contains("E") || buf_str.contains("T1") || buf_str.contains("T2"))){
        if (buf_str.contains("E")){
            buf_str = buf_str.replaceFirst("E", Ed(buf_str.length, lengh))
        }
        if (buf_str.contains("T1")){
            buf_str = buf_str.replaceFirst("T1", T1d(buf_str.length, lengh))
        }
        if (buf_str.contains("T2")){
            buf_str = buf_str.replaceFirst("T2", T2d(buf_str.length, lengh))
        }
    }
    return(buf_str)
}
