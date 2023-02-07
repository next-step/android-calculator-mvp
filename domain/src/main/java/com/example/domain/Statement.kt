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
            terms = terms + term
        }
    }

    private fun addTermWhenLastIsOperand(term: OperationTerm) {
       when(term){
           is Operand -> {}
           is Operator -> {}
       }
    }

    private fun addTermWhenLastIsOperator(term: OperationTerm) {

    }

    fun termsToString(): String {
        return if (terms.isEmpty()) "" else fold().trim()
    }

    private fun fold(): String {
        return terms.fold("") { acc: String, term: OperationTerm ->
            if (term is Operator) "$acc ${term.prime}" else "$acc ${(term as Operand).value}"
        }
    }
}