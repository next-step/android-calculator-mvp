package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord

class EvaluationRecordStore {
    private val evaluationHistory = mutableListOf<EvaluationRecord>()

    fun record(evaluationRecord: EvaluationRecord) {
        evaluationHistory.add(evaluationRecord)
    }

    fun getEvaluationHistory() = evaluationHistory.toList()
}
