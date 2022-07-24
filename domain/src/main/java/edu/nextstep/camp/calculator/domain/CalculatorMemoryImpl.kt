package edu.nextstep.camp.calculator.domain

class CalculatorMemoryImpl(
    private val calcHistory: ArrayList<String> = arrayListOf()
) : CalculatorMemory {
    companion object {
        const val SAVE_FORMAT = "%s\n= %s\n"
    }

    override fun saveExpressionRecord(expression: Expression, result: Int) {
        calcHistory.add(SAVE_FORMAT.format(expression, result))
    }

    override fun loadExpressionRecords(): List<String> {
        return calcHistory.toList()
    }

    override fun toString(): String {
        return calcHistory.joinToString("\n") { it }
    }
}