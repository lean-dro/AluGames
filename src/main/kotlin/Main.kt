package org.example

import org.example.dto.Gamer
import org.example.models.Jogo
import org.example.services.CheapSharkAPI
import org.example.utils.ValidacaoHelper
import java.time.LocalDate
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val leituraString = Scanner(System.`in`)
    var continuar = false
    do {


        println("Digite o ID do Game:")
        val id = leitura.nextInt()
        val buscaJogo = CheapSharkAPI.buscarJogo(id)
        val jogosProcurados = mutableListOf<Jogo>()


        if (buscaJogo != null) {
            val jogo: Jogo = Jogo(buscaJogo.info.title, buscaJogo.info.thumb)

            println("Deseja inserir uma descrição para o jogo? S/N")
            val opcao: String=ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)


            if (opcao.equals("s", ignoreCase = true)) {
                println("Insira a descrição do jogo: ")
                jogo.descricao = leituraString.nextLine()
            }
            println(jogo)
        } else {
            println("Jogo não encontrado.")
        }

        println("Deseja buscar outro jogo? S/N")
        val opcao: String = ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)


        continuar = opcao.equals("s", ignoreCase = true)
    }while (continuar)
    println("AluGames encerrado.")
}