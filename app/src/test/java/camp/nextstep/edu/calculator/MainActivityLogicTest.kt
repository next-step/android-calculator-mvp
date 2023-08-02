package camp.nextstep.edu.calculator

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MainActivityLogicTest {

    private var numAndOperator = "12 +"

    @Test
    fun 마지막_값을_검증한다() {
        // given: 문자열의 마지막 인덱스를 구한다.
        val lastIndex = numAndOperator.length - 1

        // when: 문자열의 마지막 값이 숫자인지 확인합니다.
        val actual = numAndOperator[lastIndex].isDigit()

        // then: 숫자일 경우 true, 숫자 이외의 것 이라면 false
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun 마지막으로_입력된_연산자를_교체한다() {
        // given: 문자열의 마지막 인덱스를 구한다.
        val lastIndex = numAndOperator.length - 1

        // when: 마지막 문자열의 값을 삭제 후 추가한다.
        numAndOperator = numAndOperator.dropLast(1)
        numAndOperator += "*"
        val actual = numAndOperator[lastIndex]

        // then: 숫자일 경우 true, 숫자 이외의 것 이라면 false
        assertThat(actual).isEqualTo('*')
    }
}