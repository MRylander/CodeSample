package rylander.codesample.rest

import org.springframework.web.bind.annotation.*
import rylander.codesample.record.Record
import rylander.codesample.record.RecordService

@RestController()
@RequestMapping("/records")
class RecordsController(val recordService: RecordService = RecordService()) {

    @PostMapping
    fun addRecord(@RequestBody rawInput: String) {
        recordService.parseAndPersistRecord(rawInput)
    }

    @GetMapping("/gender")
    fun getSortedByGender(): List<Record> {
        return recordService.getSortedByGender()
    }

    @GetMapping("/birthdate")
    fun getSortedByBirthDate(): List<Record> {
        return recordService.getSortedByBirthDate()
    }

    @GetMapping("/name")
    fun getSortedByLastNameThenFirstName(): List<Record> {
        return recordService.getSortedByLastNameThenFirstName()
    }
}
