package edu.nextstep.camp.calculator.domain

class MemoryRepository {
    private val memoryList = mutableListOf<Memory>()

    fun addMemory(memory: Memory) {
        memoryList.add(memory)
    }
}