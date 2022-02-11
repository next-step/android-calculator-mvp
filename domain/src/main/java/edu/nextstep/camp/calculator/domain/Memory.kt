package edu.nextstep.camp.calculator.domain

data class Memory(private val items: List<Item> = emptyList()) : Mode() {

    operator fun plus(value: Item) = Memory(items + value)

    override fun toString(): String {
        return items.joinToString("\n") { "${it.expression}\n= ${it.result}\n" }
    }

    companion object {
        val EMPTY = Memory()
    }

    data class Item(val expression: String, val result: Int)
}