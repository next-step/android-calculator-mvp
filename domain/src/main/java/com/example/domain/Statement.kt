package com.example.domain

class Statement(private var terms: List<OperationTerm> = emptyList()) {

    fun addTerm(term: OperationTerm) {
        when {
            terms.isEmpty() || terms.last() is Operator -> {
                addTermWhenLastIsOperator(term)
            }
            else -> {
                addTermWhenLastIsOperand(term)
            }
        }
    }

    private fun addTermWhenLastIsOperand(term: OperationTerm) {

    }

    private fun addTermWhenLastIsOperator(term: OperationTerm) {

    }

    fun termsToString(): String {
        return ""
    }
}