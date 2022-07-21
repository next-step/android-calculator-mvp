package edu.nextstep.camp.calculator.domain.contract

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.RegexUtils
import edu.nextstep.camp.calculator.domain.exception.ExpressionNotCompleteException
import edu.nextstep.camp.calculator.domain.model.ExpressionToken
import edu.nextstep.camp.calculator.domain.model.NegativeExpressionToken
import edu.nextstep.camp.calculator.domain.model.Operand
import edu.nextstep.camp.calculator.domain.model.Operator
import edu.nextstep.camp.calculator.domain.model.OtherExpressionToken
import org.jetbrains.annotations.TestOnly

interface BaseView<T> {
    var presenter: T
}

interface MainContract {
    interface View : BaseView<Presenter> { 
        fun displayExpression(expression: String)
        fun onExpressionTokenInput(expressionToken: ExpressionToken)
    }
    interface Presenter {
        fun processToken(expressionToken: ExpressionToken)
        @TestOnly fun setCurrentDisplayedText(displayedText: String)
    }

    class PresenterImpl(private val view: View) : Presenter {
        private val expressionTokenList = mutableListOf<ExpressionToken>()

        override fun processToken(expressionToken: ExpressionToken) {
            when (expressionToken) {
                is Operand -> processNumberToken(expressionToken)
                is Operator -> processOperatorToken(expressionToken)
                is OtherExpressionToken -> processOtherToken(expressionToken)
                else -> throw IllegalArgumentException("Unknown Input Type")
            }
            view.displayExpression(expressionTokenList.toExpression())
        }

        private fun processNumberToken(input: Operand) {
            expressionTokenList.run {
                add(input)
            }
        }

        private fun processOperatorToken(input: Operator) {
            expressionTokenList.run {
                lastOrNull().also { lastToken ->
                    when {
                        lastToken is Operand -> add(input)
                        input == Operator.SUBTRACTION && (lastToken is Operator || lastToken == null) -> add(
                            NegativeExpressionToken)
                        input != Operator.SUBTRACTION && lastToken is Operator -> this[lastIndex] = input
                    }
                }
            }
        }

        private fun processOtherToken(input: OtherExpressionToken) {
            when (input) {
                OtherExpressionToken.DEL -> expressionTokenList.removeLastOrNull()
                OtherExpressionToken.EQUALS -> evaluate()
                else -> throw IllegalArgumentException("Unknown Input")
            }
        }

        private fun evaluate() {
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

        @TestOnly
        override fun setCurrentDisplayedText(displayedText: String) {
            expressionTokenList.clear()
            val operands = RegexUtils.getOperandsList(displayedText)
            val operators = RegexUtils.getOperatorsList(displayedText)
            operands.forEachIndexed { index, operand ->
                operand.toString().forEach {
                    expressionTokenList.add(ExpressionToken.getFromValue(it.toString()))
                }
                operators.getOrNull(index)?.let {
                    expressionTokenList.add(ExpressionToken.getFromValue(it))
                }
            }
            view.displayExpression(expressionTokenList.toExpression())
        }
    }
}
