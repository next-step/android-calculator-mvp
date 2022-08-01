package edu.nextstep.camp.calculator.domain

@JvmInline
value class Histories private constructor(
    private val value: List<History>
) {

    operator fun plus(history: History): Histories {
        return Histories(value.toMutableList().apply {
            add(history)
        }.toList())
    }

    fun toList(): List<History> = value.toList()

    companion object {
        fun of(histories: List<History>): Histories = Histories(histories.toList())
    }

}