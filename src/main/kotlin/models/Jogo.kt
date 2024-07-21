package org.example.models

import com.google.gson.annotations.SerializedName

class Jogo(
    val titulo:String,
    val capa:String,
    )
    {
    val descricao:String = ""
    override fun toString(): String {
        return "Jogo\n\tTítulo: $titulo\n\tCapa: $capa\n\tDescrição: $descricao"
    }
}