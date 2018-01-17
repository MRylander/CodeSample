package rylander.codesample.record

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import rylander.codesample.record.RecordService.Companion.SORT_BIRTH_DATE_ASC
import rylander.codesample.record.RecordService.Companion.SORT_GENDER_ASC
import rylander.codesample.record.RecordService.Companion.SORT_GENDER_THEN_LAST_NAME_ASC
import rylander.codesample.record.RecordService.Companion.SORT_LAST_NAME_DEC
import rylander.codesample.record.RecordService.Companion.SORT_LAST_NAME_THEN_FIRST_NAME_ASC
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
    fun shouldSortByGenderLexicographicallyAndThenByLastNameAdc() {
        // Given
        val femaleAAA = Record("AAA", "fn", "Female", "color", LocalDate.of(2000, 1, 1))
        val femaleZZZ = femaleAAA.copy(lastName = "ZZZ")
        val maleAAA = femaleAAA.copy(gender = "Male")
        val maleZZZ = maleAAA.copy(lastName = "ZZZ")
        val malemmm = maleAAA.copy(lastName = "mmm")

        val unsortedList = listOf(femaleAAA, maleZZZ, maleAAA, femaleZZZ, malemmm)

        // When
        val sortedList = unsortedList.sortedWith(RecordService.SORT_GENDER_THEN_LAST_NAME_ASC)

        // Then
        assertThat(sortedList, IsIterableContainingInOrder.contains(femaleAAA, femaleZZZ, maleAAA, malemmm, maleZZZ))
    }

    @Test
    fun shouldSortLastNameDec() {
        // Given
        val AAA = Record("AAA", "fn", "Female", "color", LocalDate.of(2000, 1, 1))
        val ZZZ = AAA.copy(lastName = "ZZZ")
        val ccc = AAA.copy(lastName = "ccc")

        val unsortedList = listOf(AAA, ZZZ, ccc)

        // When
        val sortedList = unsortedList.sortedWith(RecordService.SORT_LAST_NAME_DEC)

        // Then
        assertThat(sortedList, IsIterableContainingInOrder.contains(ZZZ, ccc, AAA))
    }

    @Test
    fun shouldSortGenderAsc() {
        // Given
        val female = Record("AAA", "fn", "Female", "color", LocalDate.of(2000, 1, 1))
        val male = female.copy(gender = "Male")
        val other = female.copy(gender = "other")

        val unsortedList = listOf(male, female, other)

        // When
        val sortedList = unsortedList.sortedWith(RecordService.SORT_GENDER_ASC)

        // Then
        assertThat(sortedList, IsIterableContainingInOrder.contains(female, male, other))
    }

    @Test
    fun shouldSortLastNameThenFirstNameAsc() {
        // Given
        val georgeWashington = Record("Washington", "George", "na", "na", LocalDate.of(2000, 1, 1))
        val janeDoe = georgeWashington.copy(lastName = "Doe", firstName = "Jane")
        val johnDoe = janeDoe.copy(firstName = "John")

        val unsortedList = listOf(johnDoe, georgeWashington, janeDoe)

        // When
        val sortedList = unsortedList.sortedWith(RecordService.SORT_LAST_NAME_THEN_FIRST_NAME_ASC)

        // Then
        assertThat(sortedList, IsIterableContainingInOrder.contains(janeDoe, johnDoe, georgeWashington))
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
    fun shouldGetRecordsSortedByOption1() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getRecordsSortedByOption1()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_GENDER_THEN_LAST_NAME_ASC))
    }

    /**
     * This all tests the getSortedByBirthDate method.
     */
    @Test
    fun shouldGetRecordsSortedByOption2() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getRecordsSortedByOption2()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_BIRTH_DATE_ASC))
    }

    @Test
    fun shouldGetRecordsSortedByOption3() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getRecordsSortedByOption3()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_LAST_NAME_DEC))
    }

    @Test
    fun shouldGetRecordsSortedByGender() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getSortedByGender()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_GENDER_ASC))
    }

    @Test
    fun shouldGetSortedByLastNameThenFirstName() {
        val mockRepository = mock(RecordRepository::class.java)
        val recordService = RecordService(mockRepository)

        recordService.getSortedByLastNameThenFirstName()

        verify(mockRepository).getRecordsSorted(safeEq(SORT_LAST_NAME_THEN_FIRST_NAME_ASC))
    }
}