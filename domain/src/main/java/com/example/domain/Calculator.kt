package com.example.domain

import com.example.domain.OperatorFinder.Companion.findOperator

object Calculator {

    val currentOperandList = mutableListOf<String>()
    private var lastInputState = InputState.Init

    fun calculate(input: String): Operand {
        require(lastInputState == InputState.Operand && currentOperandList.size > 2) { "완성되지 않은 수식입니다." }
        require(input.isNotBlank()) { "빈 공백은 입력할 수 없습니다." }

        val inputOperands = input.getListByDivRest(EVEN_NUMBER)
        val inputOperators = input.getListByDivRest(ODD_NUMBER)

        val operands = inputOperands.toOperandsList()
        val operators = inputOperators.toOperatorList()

        val result =  operands.reduceIndexed { index, first, second ->
            operators[index - 1].calculate(first, second)
        }

        clearCurrentOperandList()

        return result
    }

    fun addInput(input: String): Boolean {
        when (lastInputState) {
            InputState.Init -> {
                if (input.isOperand()) {
                    addElement(input)
                    lastInputState = InputState.Operand
                    return true
                }
            }

            InputState.Operand -> {
                if (input.isOperand()) {
                    currentOperandList[currentOperandList.lastIndex] =
                        currentOperandList.last() + input
                    return true
                } else if (input.isOperator()) {
                    addElement(input)
                    lastInputState = InputState.Operator
                    return true
                }
            }

            InputState.Operator -> {
                if (input.isOperand()) {
                    addElement(input)
                    lastInputState = InputState.Operand
                    return true
                } else if (input.isOperator()) {
                    currentOperandList[currentOperandList.lastIndex] = input
                    return true
                }
            }
        }
        return false
    }

    private fun addElement(input: String) = currentOperandList.add(input)

    fun removeLastInput() {
        if (currentOperandList.isNotEmpty()) {
            when(lastInputState) {
                InputState.Init -> return
                InputState.Operand -> removeLastOperand()
                InputState.Operator -> removeLastOperator()
            }
            updateCurrentInputState()
        }
    }

    private fun removeLastOperand() {
        currentOperandList[currentOperandList.lastIndex] = currentOperandList.last().dropLast(1)
        if (currentOperandList.last().isEmpty()) {
            currentOperandList.removeLast()
        }
    }

    private fun removeLastOperator() {
        currentOperandList.removeLast()
    }

    private fun String.isOperand(): Boolean = this.toBigDecimalOrNull() != null

    private fun String.isOperator(): Boolean =
        runCatching { findOperator(this) }.getOrNull() != null

    fun clearCurrentOperandList() {
        currentOperandList.clear()
        updateCurrentInputState()
    }

    private fun updateCurrentInputState() {
        if(currentOperandList.isEmpty()) {
            lastInputState = InputState.Init
        } else if(currentOperandList.last().isOperand()) {
            lastInputState = InputState.Operand
        } else if(currentOperandList.last().isOperator()) {
            lastInputState = InputState.Operator
        }
    }

    private fun splitList(input: String): List<String> = input.split(SPACE)

    private fun String.getListByDivRest(rest: Int): List<String> = splitList(this).filterIndexed { index, _ ->
        index % 2 == rest
    }

    private fun List<String>.toOperandsList(): List<Operand> = this.map {
        Operand(it.toBigDecimal())
    }

    private fun List<String>.toOperatorList(): List<Operator> = this.map {
        findOperator(it)
    }
}

const val SPACE = " "
const val EVEN_NUMBER = 0
const val ODD_NUMBER = 1
