package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.RecordStatement

class CalculatorRepository {
    private val recordStatementList = mutableListOf<RecordStatement>()

    fun saveStatement(recordStatement: RecordStatement) {
        recordStatementList.add(
            index = 0,
            element = recordStatement
        )
    }

    fun getRecordStatement(): RecordStatement = recordStatementList.first()
}