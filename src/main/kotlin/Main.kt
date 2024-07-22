package org.example

import org.example.dto.Gamer
import org.example.models.Jogo
import org.example.services.CheapSharkAPI
import org.example.utils.ValidacaoHelper
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val leituraString = Scanner(System.`in`)
    val gamer = Gamer.criar(leituraString)
    println("Seu cadastro foi concluído! Bem vindo a AluGames!")
    println(gamer)

    var continuar = false
    do {


        println("Digite o ID do Game:")
        val id = leitura.nextInt()
        val buscaJogo = CheapSharkAPI.buscarJogo(id)



        if (buscaJogo != null) {
            val jogo: Jogo = Jogo(buscaJogo.info.title, buscaJogo.info.thumb)

            println("Deseja inserir uma descrição para o jogo? S/N")
            val opcao: String=ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)


            if (opcao.equals("s", ignoreCase = true)) {
                println("Insira a descrição do jogo: ")
                jogo.descricao = leituraString.nextLine()
            }
            println(jogo)
            gamer.historico.add(jogo)
        } else {
            println("Jogo não encontrado.")
        }

        println("Deseja buscar outro jogo? S/N")
        val opcao: String = ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)


        continuar = opcao.equals("s", ignoreCase = true)
    }while (continuar)

    gamer.exibirHistorico()

    continuar = true
    while(continuar && gamer.historico.isNotEmpty()){

        println("Deseja excluir algum jogo? S/N")
        val opcao = ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)
        if(opcao.equals("s", true)){
            println("Digite a posição que deseja excluir: ")
            var entradaValida  = false
            var indiceExclusao = 0
            do{
                indiceExclusao = leitura.nextInt()
                entradaValida = (indiceExclusao > 0 && indiceExclusao <= gamer.historico.size+1)
                if(!entradaValida){
                    println("Posição inválida!")
                }
            }while (!entradaValida)
            gamer.historico.removeAt(indiceExclusao-1)

            println("Deseja excluir outro jogo? S/N")
            val opcaoContinuar: String = ValidacaoHelper.validaEscolhaMenuSimNao(leituraString)


            continuar = opcaoContinuar.equals("s", ignoreCase = true)
            if(continuar){
                gamer.exibirHistorico()
            }
        }
    }




    println("AluGames encerrado.")
}