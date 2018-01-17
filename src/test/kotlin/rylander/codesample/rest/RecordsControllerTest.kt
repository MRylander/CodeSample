package rylander.codesample.rest

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import rylander.codesample.record.RecordService
import rylander.codesample.safeEq

class RecordsControllerTest {

    lateinit var mockRecordService: RecordService
    lateinit var recordController: RecordsController

    @Before
    fun setup() {
        mockRecordService = mock(RecordService::class.java)
        recordController = RecordsController(mockRecordService)
    }

    @Test
    fun insertRecordsShouldDelegateToRecordService() {
        val rawInput = "blabal"
        recordController.addRecord(rawInput)

        verify(mockRecordService).parseAndPersistRecord(safeEq(rawInput))
    }

    @Test
    fun getSortedByGenderShouldDelegateToRecordService() {
        recordController.getSortedByGender()

        verify(mockRecordService).getSortedByGender()
    }

    @Test
    fun getSortedByBirthdateShouldDelegateToRecordService() {
        recordController.getSortedByBirthDate()

        verify(mockRecordService).getSortedByBirthDate()
    }

    @Test
    fun getSortedByLastNameThenFirstNameShouldDelegateToRecordService() {
        recordController.getSortedByLastNameThenFirstName()

        verify(mockRecordService).getSortedByLastNameThenFirstName()
    }
}