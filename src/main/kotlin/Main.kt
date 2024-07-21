package org.example

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.example.dto.InfoJogoApiSharkReponse
import org.example.dto.JogoApiSharkResponse
import org.example.models.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandler
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)

    println("Digite o ID do Game:")
    val id = leitura.nextInt()

    val cliente: HttpClient = HttpClient.newHttpClient()
    val request: HttpRequest = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
        .build()
    val response = cliente.send(request, HttpResponse.BodyHandlers.ofString())
    val json = response.body()

    val gson = Gson()
    var jogo:Jogo

    val resultado = runCatching {
        val jogoApiShark = gson.fromJson(json, JogoApiSharkResponse::class.java)
        jogo = Jogo(capa = jogoApiShark.info.thumb, titulo = jogoApiShark.info.title)
        println(jogo)
    }
    resultado.onFailure {
        println("Jogo não existe!")
    }

}