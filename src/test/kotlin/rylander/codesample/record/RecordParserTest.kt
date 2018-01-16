package rylander.codesample.record

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.LocalDate

class RecordParserTest {
    @Test
    fun shouldParseRecordWithPipeDelimiters() {
        val rawRecord = "LastName | FirstName | Gender | FavoriteColor | 1/1/2001"
        val expectedRecord = Record(
                lastName = "LastName",
                firstName = "FirstName",
                gender = "Gender",
                favoriteColor = "FavoriteColor",
                dateOfBirth = LocalDate.of(2001, 1, 1))

        val record = RecordParser.parse(rawRecord)

        assertThat(record, `is`(expectedRecord))
    }

    @Test
    fun shouldReturnNullWhenRecordIsMissingFields() {
        val rawRecord = "LastName"

        val record = RecordParser.parse(rawRecord)

        assertThat(record, `is`(nullValue()))
    }

    @Test
    fun shouldReturnNullWhenDataOfBirthIsMalformed() {
        val rawRecord = "LastName | FirstName | Gender | FavoriteColor | Jan 1 2001"

        val record = RecordParser.parse(rawRecord)

        assertThat(record, `is`(nullValue()))
    }

}