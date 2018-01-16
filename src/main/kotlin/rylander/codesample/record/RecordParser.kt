package rylander.codesample.record

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object RecordParser {
    /**
     * This function will return a `Record` if the input line can be parsed, otherwise `null` is returned.
     */
    fun parse(line: String): Record? {
        return try {
            val split = line.split("|")
            Record(
                    lastName = split[0].trim(),
                    firstName = split[1].trim(),
                    gender = split[2].trim(),
                    favoriteColor = split[3].trim(),
                    dateOfBirth = parseDateOfBirth(split[4])
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun parseDateOfBirth(dob: String): LocalDate {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")
        return LocalDate.parse(dob.trim(), dateTimeFormatter)
    }
}