package org.example

import com.google.gson.Gson
import org.example.dto.JogoApiSharkResponse
import org.example.models.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import kotlin.system.exitProcess

fun main() {
    val leitura = Scanner(System.`in`)
    val leituraString = Scanner(System.`in`)
    println("Digite o ID do Game:")
    val id = leitura.nextInt()

    val cliente: HttpClient = HttpClient.newHttpClient()
    val request: HttpRequest = HttpRequest.newBuilder()
        .uri(URI.create("https://www.cheapshark.com/api/1.0/games?id=$id"))
        .build()
    val response = cliente.send(request, HttpResponse.BodyHandlers.ofString())
    val json = response.body()

    val gson = Gson()
    var jogo:Jogo? = null

    val resultado = runCatching {
        val jogoApiShark = gson.fromJson(json, JogoApiSharkResponse::class.java)
        jogo = Jogo(capa = jogoApiShark.info.thumb, titulo = jogoApiShark.info.title)
    }
    resultado.onFailure {
        println("Jogo não existe!")
        exitProcess(0);
    }
    resultado.onSuccess {
        println("Deseja inserir uma descrição para o jogo? S/N")
        var opcao:String

        do {
            opcao = leituraString.nextLine()
            val entradaValida = opcao.uppercase() == "S" || opcao.uppercase() == "N"
            if(!entradaValida){
                println("Opção inválida, digite novamente: ")
            }
        }while (!entradaValida)
        if(opcao.equals("s", ignoreCase = true)){
            println("Insira a descrição do jogo: ")
            jogo?.descricao = leituraString.nextLine()
        }
        println(jogo)
    }

}