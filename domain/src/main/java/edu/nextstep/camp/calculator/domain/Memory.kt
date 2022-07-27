package edu.nextstep.camp.calculator.domain

class Memory(
    private val values: List<MemoryItem> = emptyList()
) {
    operator fun plus(item:MemoryItem): Memory {
        return Memory(values + item)
    }
    fun getHistory() : List<MemoryItem> {
        return values
    }
    data class MemoryItem(val expression: String, val result: String)
}