package org.example.exceptions

class CampoVazioOuNuloException(campo:String): IllegalArgumentException("$campo vazio ou nulo") {
}