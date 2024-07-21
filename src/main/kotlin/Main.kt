package org.example

import org.example.models.Jogo
import org.example.services.CheapSharkAPI
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val leituraString = Scanner(System.`in`)
    println("Digite o ID do Game:")
    val id = leitura.nextInt()
    val buscaJogo = CheapSharkAPI.buscarJogo(id)


    if(buscaJogo != null) {
        val jogo:Jogo = Jogo(buscaJogo.info.title, buscaJogo.info.thumb)

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
            jogo.descricao = leituraString.nextLine()
        }
        println(jogo)
    }else{
        println("Jogo não encontrado.")
    }

}