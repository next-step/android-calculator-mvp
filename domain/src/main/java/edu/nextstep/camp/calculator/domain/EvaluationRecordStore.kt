package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.model.EvaluationRecord

class EvaluationRecordStore {
    private val evaluationHistory = mutableListOf<EvaluationRecord>()

    fun record(evaluationRecord: EvaluationRecord) {
        evaluationHistory.add(evaluationRecord)
    }

    fun getEvaluationHistory() = evaluationHistory.toList()

    companion object {
        @Volatile
        private var instance: EvaluationRecordStore? = null

        @JvmStatic
        fun getInstance(): EvaluationRecordStore =
            instance ?: synchronized(this) {
                instance ?: EvaluationRecordStore().also {
                    instance = it
                }
            }
    }
}
