package rylander.codesample.record

class RecordService(val recordRepository: RecordRepository = RecordRepository()) {
    companion object {
        val SORT_BIRTH_DATE_ASC = Comparator<Record> { r1, r2 -> r1.dateOfBirth.compareTo(r2.dateOfBirth) }
        val SORT_LAST_NAME_DEC = Comparator<Record> { r1, r2 -> r2.lastName.compareTo(r1.lastName, true) }
        val SORT_LAST_NAME_THEN_FIRST_NAME_ASC = Comparator<Record> { r1, r2 ->
            10 * Integer.signum(r1.lastName.compareTo(r2.lastName, true)) +
                    Integer.signum(r1.firstName.compareTo(r2.firstName, true))
        }
        val SORT_GENDER_THEN_LAST_NAME_ASC = Comparator<Record> { r1, r2 ->
            10 * Integer.signum(r1.gender.compareTo(r2.gender, true)) +
                    Integer.signum(r1.lastName.compareTo(r2.lastName, true))
        }
        val SORT_GENDER_ASC = Comparator<Record> { r1, r2 -> r1.gender.compareTo(r2.gender, true) }

    }

    fun parseAndPersistRecord(rawRecordInput: String) {
        val record = RecordParser.parse(rawRecordInput)
        record?.let { recordRepository.add(it) }
    }

    /**
     * This method return all the records sorted by `option1`. Option1 is sorted by gender (females before males) then
     * by last name ascending.
     */
    fun getRecordsSortedByOption1(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_GENDER_THEN_LAST_NAME_ASC)
    }

    /**
     * This method return all the records sorted by `option2`. Option2 is sorted by birth date, ascending.
     */
    fun getRecordsSortedByOption2(): List<Record> {
        return getSortedByBirthDate()
    }

    fun getSortedByBirthDate(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_BIRTH_DATE_ASC)
    }

    /**
     * This method return all the records sorted by `option3`. Option3 is sorted by last name, descending.
     */
    fun getRecordsSortedByOption3(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_LAST_NAME_DEC)
    }

    fun getSortedByGender(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_GENDER_ASC)
    }

    fun getSortedByLastNameThenFirstName(): List<Record> {
        return recordRepository.getRecordsSorted(SORT_LAST_NAME_THEN_FIRST_NAME_ASC)
    }

}