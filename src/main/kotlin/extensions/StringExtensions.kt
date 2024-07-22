package org.example.extensions

import java.time.LocalDate
import java.time.Period

fun LocalDate.obterIdade(): Int?{
    return Period.between(this, LocalDate.now()).years ?: null
}