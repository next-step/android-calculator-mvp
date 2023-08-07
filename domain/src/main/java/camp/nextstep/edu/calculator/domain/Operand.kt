package camp.nextstep.edu.calculator.domain

class Operand(number: Int = 0) {

    var number: Int = number
        private set

    fun addNumberText(addNumber: Int) {
        number = runCatching {
            "$number$addNumber".toInt()
        }.getOrDefault(number)
    }

    fun removeNumberText(): Boolean {
        return runCatching {
            number = number.toString().let {
                it.subSequence(0, it.length - 1).toString().toInt()
            }
            true
        }.getOrDefault(false)
    }
}
