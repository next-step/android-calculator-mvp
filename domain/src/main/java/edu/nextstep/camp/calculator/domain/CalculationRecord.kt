package edu.nextstep.camp.calculator.domain

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 23..
 */
class CalculationRecord(
    private val calculationRecordList: MutableList<Pair<String, Int>> = mutableListOf()
) {

    fun addCalculationRecord(expression: String, result: Int) {
        calculationRecordList.add(Pair(expression, result))
    }

    override fun toString(): String {
        var totalRecord = ""
        calculationRecordList.forEach {
            totalRecord += "${it.first}\n= ${it.second}\n\n"
        }
        return totalRecord
    }

}