package edu.nextstep.camp.calculator.domain

class MemoryHistorySaver : CalculatorHistorySaver {

    private val lines = mutableListOf<String>()

    override fun save(rawExpression: String, rawResult: String) {
        lines.add(rawExpression)
        lines.add("= $rawResult")
        lines.add("")
    }

    override fun loadAll(): String {
        return lines.dropLast(1).joinToString("\n")
    }
}
