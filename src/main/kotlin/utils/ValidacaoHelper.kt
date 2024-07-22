package org.example.utils

import org.example.exceptions.EmailInvalidoException
import java.util.Scanner

class ValidacaoHelper {
    companion object{
        fun validarEmail(email:String) {
            val regexEmail = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
            if (!regexEmail.matches(email)) {
                throw EmailInvalidoException()
            }
        }
        fun validaEscolhaMenuSimNao(entrada:Scanner):String{
            var opcao:String =""
            do {
                opcao = entrada.nextLine()
                val entradaValida = opcao.uppercase() == "S" || opcao.uppercase() == "N"
                if (!entradaValida) {
                    println("Opção inválida, digite novamente: ")
                }
            } while (!entradaValida)

            return opcao
        }
    }
}