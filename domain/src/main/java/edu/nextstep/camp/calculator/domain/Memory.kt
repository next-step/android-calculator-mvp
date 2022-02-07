package edu.nextstep.camp.calculator.domain

private typealias Item = Pair<String, Int>

data class Memory(private val items: List<Item> = emptyList()) : Mode() {

    operator fun plus(value: Item) = Memory(items + value)

    override fun toString(): String {
        return items.joinToString("\n") { "${it.first}\n= ${it.second}\n" }
    }

    companion object {
        val EMPTY = Memory()
    }
}