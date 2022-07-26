package edu.nextstep.camp.calculator.domain

class CalculationResultStorage(
    private val calculationResults: MutableList<CalculationResult> = mutableListOf()
) {
    fun addCalculationResult(result: CalculationResult) = calculationResults.add(result)

    fun getResultsAsList() = calculationResults.toList()

}