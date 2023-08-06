package camp.nextstep.edu.calculator.domain

class InputTextManager(private val inputTextConvertor: InputTextConvertor) {
    fun addText(prevText: String, addText: String): String {
        return runCatching {
            require(addText.isNotEmpty()) { "input text empty error" }

            if (prevText.isEmpty()) {
                getFirstInputText(prevText, addText)
            } else {
                getNumberOrOperationText(prevText, addText)
            }
        }.getOrDefault(prevText)
    }

    private fun getFirstInputText(prevText: String, addText: String): String {
        val number = inputTextConvertor.getNumberText(addText)
        return if (number == 0) prevText else number.toString()
    }

    private fun getNumberOrOperationText(prevText: String, addText: String): String {
        val splitStrings = prevText.trim().split(" ")

        return if (splitStrings.size.isOperand()) {
            getNumberText(prevText, addText)
        } else {
            getOperationText(prevText, addText)
        }
    }

    private fun getNumberText(prevText: String, addText: String): String {
        return if (Operation.findOperation(addText) != null) {
            "$prevText ${inputTextConvertor.getOperationText(addText).operationText} "
        } else {
            inputTextConvertor.getNumberText(addText).sumText(prevText)
        }
    }

    private fun getOperationText(prevText: String, addText: String): String {
        return if (Operation.findOperation(addText) != null) {
            "${prevText.subSequence(0, prevText.length - 3)} ${inputTextConvertor.getOperationText(addText).operationText} "
        } else {
            inputTextConvertor.getNumberText(addText).sumText(prevText)
        }
    }

    fun removeText(text: String): String {
        return runCatching {
            require(text.isNotEmpty()) { "input text empty error" }

            val splitStrings = text.trim().split(" ")
            val subIndex = if (splitStrings.size.isOperand()) {
                OPERAND_TEXT_REMOVE_LENGTH
            } else {
                NUMBER_TEXT_REMOVE_LENGTH
            }

            text.subSequence(0, text.length - subIndex).toString()
        }.getOrDefault(text)
    }

    fun calculateText(text: String): String {
        return runCatching {
            require(text.isNotEmpty()) { "input text empty error" }

            val splitStrings = text.trim().split(" ")

            if (splitStrings.size.isOperand()) {
                Calculator(inputTextConvertor).evaluate(text).toString()
            } else {
                text
            }
        }.getOrDefault(text)
    }

    private fun Int.isOperand(): Boolean = this % 2 == 1
    private fun Int.sumText(prevText: String): String = prevText + this.toString()

    companion object {
        const val NUMBER_TEXT_REMOVE_LENGTH = 3
        const val OPERAND_TEXT_REMOVE_LENGTH = 1
    }
}
