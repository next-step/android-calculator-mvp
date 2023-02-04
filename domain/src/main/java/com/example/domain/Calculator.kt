package com.example.domain

/**
 * # 문자열 사칙 연산 계산기 구현
 *
 * ## 기능 요구 사항
 * 1. 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
 * 2. 문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
 * 3. 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
 *
 * ## 프로그래밍 요구 사항
 * 1. 계산기 도메인과 관련된 로직은 domain 모듈에 구현한다.
 * 2. 메서드가 너무 많은 일을 하지 않도록 분리하기 위해 노력해 본다.
 *
 * ## 힌트
 * ### 테스트할 수 있는 단위로 나누어 구현 목록을 만든다.
 * - 덧셈
 * - 뺄셈
 * - 곱셈
 * - 나눗셈
 * - 입력값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException throw
 * - 사칙연산 기호가 아닌 경우 IllegalArgumentException throw
 * - 사칙 연산을 모두 포함하는 기능 구현
 * - 공백 문자열을 빈 공백 문자로 분리하려면 String 클래스의 split(" ") 메소드를 활용한다. 반복적인 패턴을 찾아 반복문으로 구현한다.
 * */
class Calculator {
    /**
     * 덧셈
     *
     * @param first 첫 번째 입력값
     * @param second 두 번째 입력값
     * */
    fun sum(first: Int, second: Int): Int {
        return first + second
    }

    /**
     * 뺄셈
     *
     * @param first 첫 번째 입력값
     * @param second 두 번째 입력값
     * */
    fun sub(first: Int, second: Int): Int {
        return first - second
    }

    /**
     * 곱셈
     *
     * @param first 첫 번째 입력값
     * @param second 두 번째 입력값
     * */
    fun mul(first: Int, second: Int): Int {
        return first * second
    }

    /**
     * 나눗셈
     *
     * @param first 첫 번째 입력값
     * @param second 두 번째 입력값
     * */
    fun div(first: Int, second: Int): Int {
        require(second != 0) {
            throw ArithmeticException("0으로 나눌 수 없습니다.")
        }

        return first / second
    }

    /**
     * 사칙 연산을 모두 포함하는 기능
     *
     * @param operations 입력된 문자열
     * */
    fun evaluate(operations: String?): Int {
        require(!operations.isNullOrEmpty()) {
            throw IllegalArgumentException("입력값이 존재하지 않습니다.")
        }

        val operationList = operations.split(" ")

        require(operationList.size % 2 != 0) {
            throw IllegalArgumentException("입력값이 존재하지 않습니다.")
        }

        operationList.forEachIndexed { index, c ->
            if (index % 2 == 0) {
                val inputNumber = c.toIntOrNull()

                when {
                    inputNumber == null -> throw IllegalArgumentException("입력값 오류")
                    inputNumber < 0 -> throw IllegalArgumentException("음수를 입력하실 수 없습니다.")
                }
            }
        }

        return calc(operationList)
    }

    /**
     * 계산식을 이용해 계산 결과 도출
     *
     * @param operationList 입력된 문자열 리스트
     * */
    private fun calc(operationList: List<String>): Int {
        var result = operationList[0].toInt()

        for (i in operationList.indices) {
            if (i % 2 != 0) {
                result = when (operationList[i]) {
                    Operator.Plus.operator -> {
                        sum(result, operationList[i + 1].toInt())
                    }

                    Operator.Minus.operator -> {
                        sub(result, operationList[i + 1].toInt())
                    }

                    Operator.MultiBy.operator -> {
                        mul(result, operationList[i + 1].toInt())
                    }

                    Operator.DividedBy.operator -> {
                        div(result, operationList[i + 1].toInt())
                    }

                    else -> throw IllegalArgumentException("잘못된 문자를 입력")
                }
            }
        }
        return result
    }

    enum class Operator(val operator: String) {
        Plus("+"), Minus("-"), MultiBy("*"), DividedBy("/")
    }
}