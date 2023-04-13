package com.example.vkr_new_disign.mathBlock

//    Функция отвечает за операции + и -
    fun E(len: Int, lengh: Int): String {
        if (len in 1..lengh){
            var l = (1..3).random()
            if (l == 1){
                return("T1+E")
            }else if(l == 2){
                return("T1-E")
            }else if(l == 3){
                return ("T1")
            }
        }else if (len > lengh){
            return ("T1")
        }else if(len == -1){
            var l = (1..2).random()
            if (l == 1){
                return ("T1+E")
            }else if(l == 2){
                return ("T1+E")
            }
        }
        return ("")
    }

//    Функция отвечает за операцию *
    fun T1(len: Int, lengh: Int):String{
        if (len < lengh){
            var l = (1..2).random()
            if (l == 1){
                return ("T1*T2")
            }else if( l == 2){
                return ("T2")
            }
        }else{
            return ("T2")
        }
        return ("")
    }

//    функция отвечает за расставление скобок
    fun T2(len: Int, lengh: Int):String{
        if (len < lengh){
            var l = (1..2).random()
            if (l == 1){
                return Nums()
            }else if(l == 2){
                var et3 = E(-1, lengh)
                var st3 = "($et3)"
                return (st3)
            }
        }else{
            return Nums()
        }
        return ("")
    }

//    функция генерирует цифры
    fun Nums():String{
        return (0..9).random().toString()
    }

//    Главная функция
    fun Gen(str: String = "E", lengh: Int): String {
        var buf_str = str
        while ((buf_str.contains("E") || buf_str.contains("T1") || buf_str.contains("T2"))){
            if (buf_str.contains("E")){
                buf_str = buf_str.replaceFirst("E", E(buf_str.length, lengh))
            }
            if (buf_str.contains("T1")){
                buf_str = buf_str.replaceFirst("T1", T1(buf_str.length, lengh))
            }
            if (buf_str.contains("T2")){
                buf_str = buf_str.replaceFirst("T2", T2(buf_str.length, lengh))
            }
        }
    return(buf_str)
    }


