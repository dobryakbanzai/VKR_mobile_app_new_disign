package com.example.vkr_new_disign.networkBlock

import io.ktor.client.*
import io.ktor.client.call.*

import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.android.*
import io.ktor.http.*
import java.net.URL

import io.ktor.client.*
import io.ktor.client.plugins.*

import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.Headers.Companion.build

import io.ktor.http.Parameters.Companion.build
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.*
import io.ktor.util.StringValues.Companion.build
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.net.URLEncoder
import java.util.*


val url = "http://192.168.1.107:8080/api/"


suspend fun getDeriv(func: String): String{

    val funcForUrl = URLEncoder.encode(func, "UTF-8")

    val uResp = URL("https://www.symja.org/v1/api?i=D(${funcForUrl},x)&f=plaintext&appid=DEMO")
    println(uResp.toString())
    val client = HttpClient(Android) {
        // конфигурация клиента
    }

    val response = client.get(uResp) {
        // дополнительная конфигурация запроса, если нужна
    }
    return getAnswDeriv(response.body())
}

fun getAnswDeriv(resp: String): String{
    println(resp)
    var json = resp
    var plaintext = ""
    val jsonObject = JSONObject(json)
    val podsArray = jsonObject.getJSONObject("queryresult").getJSONArray("pods")

    for (i in 0 until podsArray.length()) {
        val podObject = podsArray.getJSONObject(i)
        if (podObject.getString("title") == "Result") {
            val subpodObject = podObject.getJSONArray("subpods").getJSONObject(0)
            plaintext = subpodObject.getString("plaintext")


            break
        }
    }
    if (plaintext == ""){
        for (i in 0 until podsArray.length()) {
            val podObject = podsArray.getJSONObject(i)
            if (podObject.getString("title") == "Derivative") {
                val subpodObject = podObject.getJSONArray("subpods").getJSONObject(0)
                plaintext = subpodObject.getString("plaintext")
                break
            }
        }
    }

    return plaintext
}

//получение ключа RSA
suspend fun getToken(): String {

    val uResp = URL(url + "account/gettoken")

    val client = HttpClient(Android) {
        // конфигурация клиента
    }

    val response = client.get(uResp) {
        // дополнительная конфигурация запроса, если нужна
    }

    return response.body()

}


//Получение JWT-токена
suspend fun getJWT(login: String, pass: String):String{

    val uResp = URL(url + "account/gettoken")

    var body = """{"login":"$login", "password":"$pass"}"""


    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }

    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    println(body)

    return response.body<String>().toString().replace("\"", "")
}

//Получение своей информации
suspend fun getMyselfStud(token: String): String{

    val uResp = URL(url+"students/myself")
    val client = HttpClient(Android){

    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }

    if(response != null){
        println(response.body() as String)
    }else{
        println("sasfafe")
    }

    return response.body()
}

suspend fun getMyselfTeacher(token: String): String{

    val uResp = URL(url+"teachers/myself")
    val client = HttpClient(Android){

    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }

    if(response != null){
        println(response.body() as String)
    }else{
        println("sasfafe")
    }

    return response.body()
}

suspend fun addAryphProg(token: String){
    val uResp = URL(url+"students/addaryph")
    val client = HttpClient(Android){
    }

    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
}

suspend fun addDerProg(token: String): String{
    val uResp = URL(url+"students/addder")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
    return response.body() as String
}

suspend fun addZadProg(token: String): String{
    val uResp = URL(url+"students/addtask")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
    return response.body() as String
}



suspend fun getAllStud(): String{
    val uResp = URL(url+"students")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
    }
    return response.body()
}

suspend fun registrNewStudent(body: String): String {
    val uResp = URL(url + "students")
    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }
    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    println(body)

    return response.status.toString()

}

suspend fun registrNewTeacher(body: String): String {
    val uResp = URL(url + "teachers")
    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }
    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    println(body)

    return response.status.toString()

}

suspend fun getAllTeacher(): String{
    val uResp = URL(url+"teachers")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
    }
    return response.body()
}


suspend fun getAllMy(token: String): String{
    val uResp = URL(url+"challanges/mychallange")
    val client = HttpClient(Android){

    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }

    println(response.body() as String)
    return response.body() as String
}

suspend fun getAnotherChallanges(token: String): String{
    val uResp = URL(url+"challanges/anotherchallange")
    val client = HttpClient(Android){

    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
    println("-----" + response.body() as String)
    return response.body() as String
}

suspend fun acceptNewChallange(token: String, challange_id: String){

}

suspend fun applyNewChallange(token: String, chid: String){
    println(token)
    val uResp = URL(url + "challangestudents")

    var body = """ {"challangeId":"${chid}",
    "studentId":${token}} """

    println(chid)

    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }
    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }

    println(response.toString())
}

suspend fun getMyId(token: String): String{
    val uResp = URL(url+"students/myid")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
    println("-----" + response.body() as String)
    return response.body() as String
}

suspend fun getMyRole(token: String): String{
    val uResp = URL(url+"account/myrole")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
        header("Authorization", "Bearer $token")
    }
    println("-----" + response.body() as String)
    return response.body() as String
}

suspend fun addNewChallange(body: String){
    val uResp = URL(url + "challanges")
    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }
    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    println(response.body() as String)
}

suspend fun getAllZads(): String{
    val uResp = URL(url+"tasks")
    val client = HttpClient(Android){
    }
    val response = client.get(uResp){
    }
    return response.body()
}

suspend fun addNewZad(body: String){
    val uResp = URL(url+"tasks")
    val client = HttpClient(Android) {
        install(DefaultRequest) {
            headers.append("Content-Type", "application/json")
        }
        install(ContentNegotiation) {
            json()
        }
        // конфигурация клиента
    }
    val response = client.post(uResp) {
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    println(response.body() as String)
}