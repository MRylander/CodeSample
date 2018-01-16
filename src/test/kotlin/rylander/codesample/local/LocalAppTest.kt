package rylander.codesample.local

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import rylander.codesample.record.RecordService
import rylander.codesample.safeAny
import java.io.File

class LocalAppTest {

    val inputFile = createInputFile()
    lateinit var mockRecordService: RecordService
    lateinit var localApp: LocalApp

    @Before
    fun setup() {
        mockRecordService = mock(RecordService::class.java)
        localApp = LocalApp(mockRecordService)
    }

    @Test
    fun shouldIngestRecords() {
        // When a file with 3 lines is ingested
        localApp.run(arrayOf("local", "option1", inputFile.canonicalPath))

        // Expect the record service should be called to parse and persist 3 times (once per record)
        verify(mockRecordService, times(3)).parseAndPersistRecord(safeAny())
    }

    @Test
    fun shouldSortUsingOptionTwo() {
        localApp.run(arrayOf("local", "Option2", inputFile.canonicalPath))

        verify(mockRecordService).getRecordsSortedByOption2()
    }

    private fun createInputFile(): File {
        val tempFile = File.createTempFile("CodeSample", null)
        tempFile.deleteOnExit()
        tempFile.writeText("LastName | FirstName | Gender | FavoriteColor | 1/1/2001")
        tempFile.appendText("\nLastName | FirstName | Gender | FavoriteColor | 1/1/2003")
        tempFile.appendText("\nLastName | FirstName | Gender | FavoriteColor | 1/1/2002")
        return tempFile
    }
}