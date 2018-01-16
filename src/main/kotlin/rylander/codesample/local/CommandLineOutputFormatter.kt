package rylander.codesample.local

import rylander.codesample.record.Record
import java.time.format.DateTimeFormatter

object CommandLineOutputFormatter {
    private val dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")

    fun format(record: Record): String {
        return "${record.lastName}, ${record.firstName}, ${record.gender}, ${record.favoriteColor}, ${record.dateOfBirth.format(dateFormatter)}"
    }
}