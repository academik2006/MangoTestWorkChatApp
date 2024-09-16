package com.mangotestworkchat.app.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetZodiacSignUseCase @Inject constructor() {

    fun getZodiacSign(birthDate: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val lunarMonth = birthDate.format(formatter).substring(0, 2).toInt()

        val zodiacSigns = listOf(
            "Овен", "Телец", "Близнецы", "Рак",
            "Лев", "Дева", "Весы", "Скорпион",
            "Стрелец", "Козерог", "Водолей", "Рыбы"
        )

        val zodiacMonths = listOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        )

        val lunarMonths = listOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
        )

        val index = zodiacMonths.indexOf(lunarMonths[lunarMonth])
        return zodiacSigns[index]
    }

    // Пример использования
    //val birthDate = LocalDate.of(1990, 6, 15)
    //println(getZodiacSign(birthDate)) // Выведет: Близнецы
}