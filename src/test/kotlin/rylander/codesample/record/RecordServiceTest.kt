package rylander.codesample.record

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import rylander.codesample.record.RecordService.Companion.SORT_BIRTH_DATE_ASC
import rylander.codesample.safeEq
import java.time.LocalDate

class RecordServiceTest {
    @Test
    fun shouldSortByBirthDateAsc() {
        val record2000 = Record("ln", "fn", "gender", "color", LocalDate.of(2000, 1, 1))
        val record2001 = record2000.copy(dateOfBirth = LocalDate.of(2001, 1, 1))

        val firstLessThanSecond = RecordService.SORT_BIRTH_DATE_ASC.compare(record2000, record2001)
        assertThat(firstLessThanSecond, lessThan(0))

        val firstEqualSecond = RecordService.SORT_BIRTH_DATE_ASC.compare(record2000, record2000)
        assertThat(firstEqualSecond, equalTo(0))

        val firstGreaterThanSecond = RecordService.SORT_BIRTH_DATE_ASC.compare(record2001, record2000)
        assertThat(firstGreaterThanSecond, greaterThan(0))
    }


    @Test
    fun shouldParseAndPersist() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)
        val expectedRecord = Record("ln", "fn", "gender", "color", LocalDate.of(2001, 1, 1))

        recordService.parseAndPersistRecord("ln|fn|gender|color|1/1/2001")

        verify(mockRepository).add(safeEq(expectedRecord))
    }

    @Test
    fun shouldGetRecordsSortedByOption2() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getRecordsSortedByOption2()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_BIRTH_DATE_ASC))
    }
}