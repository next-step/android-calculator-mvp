package edu.nextstep.camp.calculator.domain.expression

data class Expressions(private val expressionList: List<Expression> = emptyList()) {
    private var content: MutableList<Expression> = mutableListOf<Expression>().apply {
        addAll(expressionList)
    }

    fun calculate(): Double = content.reduce { first, second ->
        when {
            first is Operating && second is Operand -> first + second
            first is Operand && second is Operator -> first + second
            first is Operator && second is Operand -> second + first
            first is Operand && second is Operating -> second + first
            else -> throw IllegalArgumentException("잘못된 연산자가 포함되었습니다.")
        }
    }.let {
        (it as? Operand)?.number ?: throw IllegalArgumentException("완성되지 않은 수식입니다.")
    }

    fun evalute(): String = content.reduce { first, second ->
        when {
            first is Operating && second is Operand -> first + second
            first is Operand && second is Operator -> first + second
            first is Operator && second is Operand -> second + first
            first is Operand && second is Operating -> second + first
            else -> throw IllegalArgumentException("잘못된 연산자가 포함되었습니다.")
        }
    }.let {
        (it as? Operand)?.toString() ?: throw IllegalArgumentException("완성되지 않은 수식입니다.")
    }

    operator fun plus(value: Int): Expressions = this.also { content.add(Operand(value.toDouble())) }
    operator fun plus(value: Operator) = this.also {
        if (content.isEmpty()) return@also

        when(content.lastOrNull()) {
            is Operator -> content.apply {
                removeLast()
                add(value)
            }
            is Operand -> content.add(value)
            else -> {}
        }
    }

    fun removeLast(): Expressions {
        content.removeLastOrNull()
        return this
    }

    override fun toString(): String {
        return content.takeIf{ it.isNotEmpty() }?.map {
            it.toString()
        }?.reduce { left, right -> when{
            left.last().isDigit() && right.last().isDigit() -> "$left$right"
            else -> "$left $right"
        }
        } ?: ""
    }
}