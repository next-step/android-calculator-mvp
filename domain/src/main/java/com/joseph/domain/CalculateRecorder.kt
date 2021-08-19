package com.joseph.domain

class CalculateRecorder(
    records: List<CalculateRecord> = emptyList()
) {

    private val _records: MutableList<CalculateRecord> = records.toMutableList()
    val records get() = _records.toList()

    fun recordCalculate(record: CalculateRecord) {
        _records.add(record)
    }
}