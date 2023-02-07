package com.example.domain

data class Statement(private var terms: List<OperationTerm> = emptyList()) {

    fun addTerm(term: OperationTerm) {
        when {
            terms.isEmpty() -> {
                addTermWhenEmpty(term)
            }
            terms.last() is Operator -> {
                addTermWhenLastIsOperator(term)
            }
            else -> {
                addTermWhenLastIsOperand(term)
            }
        }
    }

    fun removeTerm() {

    }

    fun calculate(): Int {
        val calculator: Calculator = Calculator()
        return calculator.calculate(termsToString())
    }

    private fun addTermWhenEmpty(term: OperationTerm) {
        if (term is Operand) {
            terms + term
        }
    }

    private fun addTermWhenLastIsOperand(term: OperationTerm) {
        if (term is Operand) {

        }
    }

    private fun addTermWhenLastIsOperator(term: OperationTerm) {

    }

    fun termsToString(): String {
        return ""
    }
}