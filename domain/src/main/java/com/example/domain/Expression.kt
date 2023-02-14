package com.example.domain

data class Expression(private val elements: List<Any> = emptyList()) {

    // 순수 함수 - 같은 인자에 대해 항상 같은 값 반환, 함수 외부의 어떤 상태도 바꾸지 않음
    fun appendOperator(operator: Operator): Expression {
        val elementList: MutableList<Any> = mutableListOf()
        elementList.addAll(elements) // 깊은 복사

        when (elementList.lastOrNull()) {
            is Operator -> {
                elementList.removeLast()
                elementList.add(operator)
            }

            is Int -> {
                elementList.add(operator)
            }
        }
        return Expression(elementList)
    }

    fun appendOperand(operand: Int): Expression {
        val elementList: MutableList<Any> = mutableListOf()
        elementList.addAll(elements)

        when (val lastElement = elementList.lastOrNull()) {
            is Operator -> {
                elementList.add(operand)
            }

            is Int -> {
                elementList.removeLast()
                elementList.add(lastElement * 10 + operand)
            }

            null -> {
                elementList.add(operand)
            }
        }
        return Expression(elementList)
    }

    fun removeLastValue(): Expression {
        var elementList: MutableList<Any> = mutableListOf()
        elementList.addAll(elements)

        when (val lastElement = elementList.lastOrNull()) {
            is Operator -> {
                elementList.removeLast()
            }

            is Int -> {
                elementList = removeUnits(lastElement, elementList) // 얕은 복사
            }
        }
        return Expression(elementList)
    }

    private fun removeUnits(lastElement: Int, elementList: MutableList<Any>): MutableList<Any> {
        if (lastElement < 10) {
            elementList.removeLast()
        } else {
            elementList.removeLast()
            elementList.add(lastElement / 10)
        }
        return elementList
    }

    fun getExpressions(): String {
        return elements.joinToString(separator = " ") { getExpressionToAppend(it) }
    }

    private fun getExpressionToAppend(element: Any): String {
        return when (element) {
            is Operator -> element.op
            is Int -> element.toString()
            else -> ""
        }
    }
}