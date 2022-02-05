package edu.nextstep.camp.calculator.domain

class Memory {
    val memories = mutableListOf<String>()

    fun add(expression: Expression, result: Int) {
        val memory: String = "$expression\n= $result"
        memories.add(memory)
    }
}