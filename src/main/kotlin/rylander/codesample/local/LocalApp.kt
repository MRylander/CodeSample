package rylander.codesample.local

import rylander.codesample.record.RecordService
import java.io.File

/**
 * LocalApp runs the application as a command line application.
 *
 * The command line app is run by executing the jar with the `local` mode option, followed by the desired output
 * format, and a list of file paths that correspond to the data input files.
 *
 *     java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar [modeOption] [outputFormat] [inputFilePaths]...
 *
 * Valid output formats:
 *   - `option1`: sorted by gender (females before males) then by last name ascending.
 *   - `option2`: sorted by birth date, ascending.
 *   - `option3`: sorted by last name, descending.
 *
 * Example: This example would parse the contents of 'testData-pipes1.txt' located in the testData directory of the
 * project repo and then output to the terminal the records sorted via option2.
 *
 *     # From the root of the project repo
 *     # Compile Executable Jar
 *     gradlew jar
 *
 *     # Run the app
 *     java -jar build/libs/CodeSample_GR-0.1-SNAPSHOT.jar local option2 testData/testData-pipes1.txt
 */
class LocalApp(val recordService: RecordService = RecordService()) {
    fun run(args: Array<String>) {
        ingestRecords(args.drop(2))

        // Print the data in the selected output format
        when (args[1].toLowerCase()) {
            "option1" -> recordService.getRecordsSortedByOption1()
            "option2" -> recordService.getRecordsSortedByOption2()
            "option3" -> recordService.getRecordsSortedByOption3()
            else -> {
                println("Invalid outputFormat.")
                emptyList()
            }
        }.forEach { println(CommandLineOutputFormatter.format(it)) }
    }

    fun ingestRecords(filePaths: List<String>) {
        filePaths.forEach { filePath ->
            try {
                File(filePath).forEachLine { recordService.parseAndPersistRecord(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}