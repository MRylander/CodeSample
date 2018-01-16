package rylander.codesample.record

import java.time.LocalDate

data class Record(
        val lastName: String,
        val firstName: String,
        val gender: String,
        val favoriteColor: String,
        val dateOfBirth: LocalDate)