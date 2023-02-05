package com.example.domain


class OperationParser {

    fun parse(operation: String): List<OperationTerm> {
        require(operation.isNotBlank()) {
            "빈 공백 혹은 문자열은 입력하실 수 없습니다"
        }
        val terms = operation.split(' ')
        require(isCompleteOperation(terms.size)) {
            "완성되지 않은 수식입니다."
        }
        return terms.mapIndexed { index: Int, term: String ->
            toOperationTerm(term, index)
        }
    }

    private fun toOperationTerm(term: String, index: Int): OperationTerm {
        return if (index % 2 == 0) {
            Operand.fromTerm(term)
        } else {
            Operator.getByPrime(term)
        }
    }

    private fun isCompleteOperation(size: Int): Boolean {
        return size % 2 != 0
    }
}