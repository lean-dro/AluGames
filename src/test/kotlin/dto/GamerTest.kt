package dto

import org.example.dto.Gamer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class GamerTest{
    var gamer: Gamer? =null
    @BeforeEach
    fun init(){
        gamer.let {
            it?.user= "Leandro"
            it?.email ="leandrinho@gmail.com"
            it?.dataNascimento = LocalDate.parse("2004-07-24")
            it?.user="leandrinho"
        }
    }

    @Test
    fun testaConstrutorCheio(){


        println(gamer)
        assertEquals("Leandro", gamer?.nome)
    }

    @Test
    fun testaConstrutor(){

        val gamer = Gamer(
            "Leandro",
            "leandrinho@gmail.com",
            )

        assertEquals(null, gamer.dataNascimento)
        gamer.let {
            it.dataNascimento = LocalDate.parse("2004-07-24")
        }.also {
            println(gamer.id)
        }
        assertEquals("2004-07-24", gamer.dataNascimento.toString())
    }

}