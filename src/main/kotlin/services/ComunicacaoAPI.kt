package org.example.services

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ComunicacaoAPI {
    companion object{
        fun get(endereco:String): String {
            val cliente: HttpClient = HttpClient.newHttpClient()
            val request: HttpRequest = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build()
            val response = cliente.send(request, HttpResponse.BodyHandlers.ofString())
            val json = response.body()
            return json
        }
    }

}