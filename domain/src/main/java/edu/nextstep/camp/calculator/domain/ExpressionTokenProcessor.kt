package edu.nextstep.camp.calculator.domain

import edu.nextstep.camp.calculator.domain.exception.ExpressionNotCompleteException
import edu.nextstep.camp.calculator.domain.model.EvaluationRecord
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.NegativeExpressionToken
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import org.jetbrains.annotations.TestOnly

class ExpressionTokenProcessor {
    private val expressionTokenList = mutableListOf<ExpressionToken>()

    fun processNumberInput(input: Operand) : String {
        return expressionTokenList.run {
            add(input)
            toExpression()
        }
    }

    fun processOperatorInput(input: Operator) : String {
        return expressionTokenList.run {
            lastOrNull().also { lastToken ->
                when {
                    lastToken is Operand -> add(input)
                    input == Operator.SUBTRACTION && (lastToken is Operator || lastToken == null) -> add(NegativeExpressionToken)
                    input != Operator.SUBTRACTION && lastToken is Operator -> this[lastIndex] = input
                }
            }
            toExpression()
        }
    }

    fun delete() : String {
        return expressionTokenList.run {
            removeLastOrNull()
            toExpression()
        }
    }

    fun evaluate(): String {
        val parsedStr = expressionTokenList.toExpression()
        if (!RegexUtils.checkExpressionIsValid(parsedStr)) {
            throw ExpressionNotCompleteException()
        }
        val result = Calculator.evaluate(parsedStr)
        expressionTokenList.clear()
        val calculatedTokens = result.toString().map {
            val token = ExpressionToken.getFromValue(it.toString())
            if (token == Operator.SUBTRACTION) NegativeExpressionToken else token
        }
        expressionTokenList.addAll(calculatedTokens)

        EvaluationRecordStore.getInstance().record(EvaluationRecord(parsedStr, result.toString()))

        return expressionTokenList.toExpression()
    }
    
    private fun List<ExpressionToken>.toExpression() : String {
        return joinToString(separator = "") {
            if (it is Operator) {
                " ${it.value} "
            } else {
                it.value.toString()
            }
        }
    }
}
