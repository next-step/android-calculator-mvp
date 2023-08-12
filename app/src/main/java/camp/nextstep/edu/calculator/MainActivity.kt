package camp.nextstep.edu.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operand
import camp.nextstep.edu.calculator.domain.ExpressionItem.Operation

class MainActivity : AppCompatActivity(), Contract.View {

    override val presenter: Contract.Presenter = ExpressionPresenter(this)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener { addOperand("0") }
        binding.button1.setOnClickListener { addOperand("1") }
        binding.button2.setOnClickListener { addOperand("2") }
        binding.button3.setOnClickListener { addOperand("3") }
        binding.button4.setOnClickListener { addOperand("4") }
        binding.button5.setOnClickListener { addOperand("5") }
        binding.button6.setOnClickListener { addOperand("6") }
        binding.button7.setOnClickListener { addOperand("7") }
        binding.button8.setOnClickListener { addOperand("8") }
        binding.button9.setOnClickListener { addOperand("9") }

        binding.buttonPlus.setOnClickListener { addOperation(Operation.Addition) }
        binding.buttonMinus.setOnClickListener { addOperation(Operation.Subtraction) }
        binding.buttonMultiply.setOnClickListener { addOperation(Operation.Multiplication) }
        binding.buttonDivide.setOnClickListener { addOperation(Operation.Division) }

        binding.buttonDelete.setOnClickListener { removeExpressionItem() }
        binding.buttonEquals.setOnClickListener { calculate() }
    }

    override fun display(text: String) {
        binding.textView.text = text
    }

    override fun displayExpressionError() {
        Toast.makeText(this@MainActivity, "완성되지 않은 수식입니다", Toast.LENGTH_SHORT).show()
    }

    private fun addOperand(inputText: String) {
        presenter.addOperandExpression(Operand(inputText.toInt()))
    }

    private fun addOperation(operation: Operation) {
        presenter.addOperationExpression(operation)
    }

    private fun removeExpressionItem() {
        presenter.removeExpressionItem()
    }

    private fun calculate() {
        presenter.calculate()
    }
}
