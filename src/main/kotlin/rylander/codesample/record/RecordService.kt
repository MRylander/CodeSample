package rylander.codesample.record

class RecordService(val recordRepository: RecordRepository = RecordRepository()) {
    companion object {
        val SORT_BIRTH_DATE_ASC = Comparator<Record> { r1, r2 -> r1.dateOfBirth.compareTo(r2.dateOfBirth) }
    }

    fun parseAndPersistRecord(rawRecordInput: String) {
        val record = RecordParser.parse(rawRecordInput)
        record?.let { recordRepository.add(it) }
    }

    /**
     * This method return all the records sorted by `option2`. Option2 is sorted by birth date, ascending.
     */
    fun getRecordsSortedByOption2(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_BIRTH_DATE_ASC)
    }
}