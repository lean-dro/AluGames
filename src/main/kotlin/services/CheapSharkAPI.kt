package org.example.services

import com.google.gson.Gson
import org.example.dto.JogoApiSharkResponse

class CheapSharkAPI {
    companion object{
        fun buscarJogo(id: Int): JogoApiSharkResponse?{

            val enderecoBusca = "https://www.cheapshark.com/api/1.0/games?id=$id"
            val json = ComunicacaoAPI.get(enderecoBusca)
            val gson = Gson()
            var jogoApiShark: JogoApiSharkResponse? = null

            val resultadoParse = runCatching {
                jogoApiShark = gson.fromJson(json, JogoApiSharkResponse::class.java)
            }
            return jogoApiShark
        }
    }

}