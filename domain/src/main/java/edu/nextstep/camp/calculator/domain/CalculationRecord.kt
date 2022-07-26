package edu.nextstep.camp.calculator.domain

/**
 * 클래스에 대한 간단한 설명이나 참고 url을 남겨주세요.
 * Created by jeongjinhong on 2022. 07. 23..
 */
class CalculationRecord(
    val calculationRecordList: MutableList<CalculationRecordItem> = mutableListOf()
) {
    fun addCalculationRecord(expression: String, result: Int) {
        calculationRecordList.add(CalculationRecordItem(expression, result))
    }
}