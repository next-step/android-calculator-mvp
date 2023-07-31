package camp.nextstep.edu.calculator.domain

class InputTextConvertor {
    fun getNotEmptyText(text: String?): String {
        require(!text.isNullOrBlank()) { "null Or Empty" }
        return text
    }

    fun getNumberText(text: String?): Int {
        return getNotEmptyText(text).let {
            val convertNumber = it.trim().toIntOrNull()
            require(convertNumber != null) { "number text error" }
            convertNumber
        }
    }

    fun getOperationText(text: String?): Operations {
        return getNotEmptyText(text).let {
            val convertOperation = Operations.findOperation(it.trim())
            require(convertOperation != null) { "Operation text error" }
            convertOperation
        }
    }
}
