package rylander.codesample.record

class RecordRepository {
    private val records = mutableListOf<Record>()

    fun add(record: Record) {
        records.add(record)
    }

    /**
     * Returns an immutable list that contains all the records.
     */
    fun getAll(): List<Record> {
        return records.toList()
    }

    /**
     * Returns an immutable list that contains all the records sorted using the give [Comparator].
     */
    fun getRecordsSorted(comparator: Comparator<Record>): List<Record> {
        return records.sortedWith(comparator)
    }
}