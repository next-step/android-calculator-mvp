package edu.nextstep.camp.calculator.domain

class CalculatorMemory(
    private val calcHistory: MutableList<String> = mutableListOf()
) {
    fun saveExpressionRecord(expression: Expression, result: Int) {
        calcHistory.add(SAVE_FORMAT.format(expression, result))
    }

    fun loadExpressionRecords(): List<String> {
        return calcHistory.toList()
    }

    override fun toString(): String {
        return calcHistory.joinToString("\n")
    }

    companion object {
        const val SAVE_FORMAT = "%s\n= %s\n"
    }
}