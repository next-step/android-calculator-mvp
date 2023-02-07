package com.example.domain

data class Statement(private var terms: List<OperationTerm> = emptyList()) {

    fun addTerm(term: OperationTerm) {
        when (terms.lastOrNull()) {
            is Operator -> {
                addTermWhenLastIsOperator(term)
            }
            is Operand -> {
                addTermWhenLastIsOperand(term)
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

    private fun addTermWhenLastIsOperand(term: OperationTerm) {
        when (term) {
            is Operand -> {
                val last = terms.last() as Operand
                terms = terms - last
                terms = terms + Operand.fromTerm("${last.value}${term.value}")
            }
            is Operator -> {
                terms = terms + term
            }
        }
    }

    private fun addTermWhenLastIsOperator(term: OperationTerm) {
        if (term is Operator) {
            val last = terms.last()
            terms = terms - last
        }
        terms = terms + term
    }


    fun removeTerm() {
        when (terms.lastOrNull()) {
            is Operator -> {
                terms = terms - terms.last()
            }
            is Operand -> {
                removeTermWhenLastIsOperand()
            }
        }
    }

    private fun removeTermWhenLastIsOperand() {
        if ((terms.last() as Operand).value >= 10) {
            val last = terms.last() as Operand
            terms = terms - terms.last()
            val remainder = (last.value - last.value % 10) / 10
            terms = terms + Operand(remainder)
        } else {
            terms = terms - terms.last()
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