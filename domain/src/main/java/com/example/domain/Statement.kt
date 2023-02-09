package com.example.domain

data class Statement(private var terms: List<OperationTerm> = emptyList()) {

    fun addTerm(term: OperationTerm) {
        when (val last = terms.lastOrNull()) {
            is Operator -> {
                addTermWhenLastIsOperator(term, last)
            }
            is Operand -> {
                addTermWhenLastIsOperand(term, last)
            }
            null -> {
                addTermWhenEmpty(term)
            }
        }
    }

    private fun addTermWhenEmpty(term: OperationTerm) {
        if (term is Operand) {
            terms = terms + term
        }
    }

    private fun addTermWhenLastIsOperand(term: OperationTerm, last: Operand) {
        when (term) {
            is Operand -> {
                terms = terms - last
                terms = terms + Operand.fromTerm("${last.value}${term.value}")
            }
            is Operator -> {
                terms = terms + term
            }
        }
    }

    private fun addTermWhenLastIsOperator(term: OperationTerm, last: Operator) {
        if (term is Operator) {
            terms = terms - last
        }
        terms = terms + term
    }


    fun removeTerm() {
        when (val last = terms.lastOrNull()) {
            is Operator -> {
                terms = terms - last
            }
            is Operand -> {
                removeTermWhenLastIsOperand(last)
            }
        }
    }

    private fun removeTermWhenLastIsOperand(last: Operand) {
        terms = terms - last
        if (last.value >= 10) {
            val remainder = (last.value - last.value % 10) / 10
            terms = terms + Operand(remainder)
        }
    }

    fun termsToString(): String {
        return if (terms.isEmpty()) "" else fold()
    }

    private fun fold(): String {
        return terms.fold("") { acc: String, term: OperationTerm ->
            if (term is Operator) "$acc ${term.prime}" else "$acc ${(term as Operand).value}"
        }.trim()
    }
}