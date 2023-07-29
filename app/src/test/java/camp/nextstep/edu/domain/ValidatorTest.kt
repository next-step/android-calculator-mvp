package camp.nextstep.edu.domain

import org.junit.Assert.*
import org.junit.Test

class ValidatorTest {

    private val validator = Validator()

    @Test
    fun `숫자가 연속으로 입력된 경우 예외를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(listOf("1", "2", "4"))
        }
    }

    @Test
    fun `사칙연산 기호가 연속으로 나타난 경우 예외를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(listOf("1", "+", "-"))
        }
    }

    @Test
    fun `빈 공백 문자열이 입력된 경우 예외를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(listOf("1", "+", ""))
        }
    }

    @Test
    fun `null 이 입력값으로 들어온 경우 예외를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(listOf("1", "+", "null"))
        }
    }

    @Test
    fun `사칙연산 기호가 아닌 경우 예외를 뱉는다`() {
        assertThrows(IllegalArgumentException::class.java) {
            validator.validate(listOf("1", "^^", "4"))
        }
    }

    @Test
    fun `정상적인 사칙연산이 들어온 경우 예외를 뱉지 않는다`() {
        try {
            validator.validate(listOf("1", "÷", "4"))
        } catch (e: IllegalArgumentException) {
            fail("Expected no exception, but caught: ${e.message}")
        }
    }
}