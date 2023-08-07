/**
 * @author Daewon on 07,August,2023
 *
 */

package com.example.domain

object Expression {

    val currentOperandList = mutableListOf<String>()
    private var lastInputState = InputState.Init

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
        runCatching { OperatorFinder.findOperator(this) }.getOrNull() != null

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

    fun isValidExpression() {
        require(lastInputState == InputState.Operand && currentOperandList.size > 2) { "완성되지 않은 수식입니다." }
    }
}
