package rylander.codesample.local

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import rylander.codesample.record.Record
import java.time.LocalDate

class CommandLineOutputFormatterTest {
    @Test
    fun shouldFormatRecord() {
        val record = Record(
                lastName = "LastName",
                firstName = "FirstName",
                gender = "Gender",
                favoriteColor = "FavoriteColor",
                dateOfBirth = LocalDate.of(2001, 1, 2))

        val formattedOutput = CommandLineOutputFormatter.format(record)

        assertThat(formattedOutput, `is`("LastName, FirstName, Gender, FavoriteColor, 1/2/2001"))
    }
}