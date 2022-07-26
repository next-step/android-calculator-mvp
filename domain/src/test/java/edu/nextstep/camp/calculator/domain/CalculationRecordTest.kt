package edu.nextstep.camp.calculator.domain

import com.google.common.truth.Truth
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * CalculationRecord 유닛테스트
 * Created by jeongjinhong on 2022. 07. 24..
 */
class CalculationRecordTest {
    private lateinit var calculationRecord: CalculationRecord

    @BeforeEach
    fun setUp() {
        calculationRecord = CalculationRecord()
    }

    @Test
    fun `계산기록이 추가되면 저장되어야한다`() {
        // when
        calculationRecord.addCalculationRecord("1 + 1", 2)

        // then
        val record = calculationRecord.calculationRecordList[0]
        Truth.assertThat(record.expression).isEqualTo("1 + 1")
        Truth.assertThat(record.result).isEqualTo(2)
    }


}