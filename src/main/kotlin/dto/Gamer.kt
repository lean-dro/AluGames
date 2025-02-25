package org.example.dto

import org.example.exceptions.CampoVazioOuNuloException
import org.example.extensions.obterIdade
import org.example.models.Jogo
import org.example.utils.ValidacaoHelper
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Scanner
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

data class Gamer(val nome:String, var email:String){
    var dataNascimento:LocalDate? = null
    var historico:MutableList<Jogo> = mutableListOf()


    var user:String? = null
        set(value) {
            field = value
            if(id.isNullOrBlank()){
                gerarID()
            }
        }

    var id:String? = null
        private set


    constructor(nome:String, email: String, dataNascimento:LocalDate, user:String): this(nome, email){
        this.dataNascimento = dataNascimento
        this.user = user
        gerarID()
    }
    init {
        ValidacaoHelper.validarEmail(email)
      if(nome.isNullOrBlank()){
            throw CampoVazioOuNuloException("Nome")
       }
    }

    fun gerarID(){
        id = "$user#"+ String.format("%04d", Random.nextInt(0,9999))
    }
    fun exibirHistorico(){
        println("Seu histórico: ")
        historico.sortBy {
            it.titulo
        }
        var indice = 1
        historico.forEach {

            println("$indice - ${it.titulo}")
            indice++
        }
    }
    companion object{
        fun criar(leitura:Scanner):Gamer{
            println("Bem vindo ao Cadastro da AluGames!")
            println("Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu Email:")
            val email = leitura.nextLine()
            println("Estamos quase lá. Deseja cadastrar sua data de nascimento e um User personalizado? S/N")
            val opcao = ValidacaoHelper.validaEscolhaMenuSimNao(leitura)
            if(opcao.equals("s", ignoreCase = true)){
                println("Digite sua data de nascimento:")
                val dataNascimento = leitura.nextLine()
                if(dataNascimento.isNullOrBlank()){
                    throw CampoVazioOuNuloException("Data de nascimento")
                }
                var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")


                println("Digite seu User:")
                val user = leitura.nextLine()
                if(user.isNullOrBlank()){
                    throw CampoVazioOuNuloException("User")
                }


                return Gamer(nome, email, LocalDate.parse(dataNascimento, formatter), user)
            }
            return Gamer(nome, email)
        }
    }
    override fun toString(): String {

        return "Gamer\n\tNome:$nome\n\tIdade:${dataNascimento?.obterIdade()}\n\tEmail:$email, Data de Nascimento:$dataNascimento\n\tUser:$user\n\tID:$id"
    }
}
