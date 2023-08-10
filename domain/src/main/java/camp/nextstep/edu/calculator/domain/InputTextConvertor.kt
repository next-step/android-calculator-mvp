package camp.nextstep.edu.calculator.domain

import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation

class InputTextConvertor {
    fun getSplitStrings(input: String?): List<String> {
        return getNotEmptyText(input).let {
            val splitStrings = it.split(splitText)
            require(splitStrings.isNotEmpty()) { "input text array empty error" }
            require(splitStrings.size > 2) { "input text array count not enough error" }
            require(splitStrings.size % 2 == 1) { "input text array count incorrect error" }
            splitStrings
        }
    }

    fun getNumberText(text: String?): Int {
        return getNotEmptyText(text).let {
            val convertNumber = it.trim().toIntOrNull()
            require(convertNumber != null) { "number text error" }
            convertNumber
        }
    }

    fun getOperationText(text: String?): Operation {
        return getNotEmptyText(text).let {
            val convertOperation = Operation.findOperation(it.trim())
            require(convertOperation != null) { "Operation text error" }
            convertOperation
        }
    }

    private fun getNotEmptyText(text: String?): String {
        require(!text.isNullOrBlank()) { "null Or Empty" }
        return text
    }

    companion object {
        private const val splitText = " "
    }
}
