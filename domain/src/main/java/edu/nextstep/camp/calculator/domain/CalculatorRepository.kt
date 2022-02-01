package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.RecordStatement

class CalculatorRepository {
    private val _recordStatementList = mutableListOf<RecordStatement>()
    val recordStatementList: List<RecordStatement> = _recordStatementList

    fun saveStatement(recordStatement: RecordStatement) {
        _recordStatementList.add(
            index = 0,
            element = recordStatement
        )
    }
}