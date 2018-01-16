package rylander.codesample.record

import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.util.*

class RecordRepositoryTest {

    lateinit var recordRepository: RecordRepository

    @Before
    fun setup() {
        recordRepository = RecordRepository()
    }

    @Test
    fun addRecord() {
        val record = Record(
                lastName = "LastName",
                firstName = "FirstName",
                gender = "Gender",
                favoriteColor = "FavoriteColor",
                dateOfBirth = LocalDate.of(2001, 1, 1))

        recordRepository.add(record)

        assertThat(recordRepository.getAll(), hasItem(record))
    }

    @Test
    fun getSorted() {
        // Given 3 records that are inserted out of order
        val record0 = Record(
                lastName = "LastName",
                firstName = "0",
                gender = "Gender",
                favoriteColor = "FavoriteColor",
                dateOfBirth = LocalDate.of(2001, 1, 1))
        val record1 = record0.copy(firstName = "1")
        val record2 = record0.copy(firstName = "2")

        recordRepository.add(record1)
        recordRepository.add(record0)
        recordRepository.add(record2)

        // When the records are retrieve sorted
        val orderComparator = Comparator<Record> { r1, r2 -> r1.firstName.compareTo(r2.firstName) }
        val sortedRecords = recordRepository.getRecordsSorted(orderComparator)

        // Then they should be sorted
        assertThat(sortedRecords, IsIterableContainingInOrder.contains(record0, record1, record2))
    }
}