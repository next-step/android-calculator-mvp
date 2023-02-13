package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)
        initButtonClickListener()
    }

    /**
     * 버튼 Set Click Listener
     * */
    private fun initButtonClickListener() {
        addOperandBy(binding.button0, 0)
        addOperandBy(binding.button1, 1)
        addOperandBy(binding.button2, 2)
        addOperandBy(binding.button3, 3)
        addOperandBy(binding.button4, 4)
        addOperandBy(binding.button5, 5)
        addOperandBy(binding.button6, 6)
        addOperandBy(binding.button7, 7)
        addOperandBy(binding.button8, 8)
        addOperandBy(binding.button9, 9)

        addOperatorBy(binding.buttonPlus, Operator.Plus)
        addOperatorBy(binding.buttonMinus, Operator.Minus)
        addOperatorBy(binding.buttonMultiply, Operator.MultiBy)
        addOperatorBy(binding.buttonDivide, Operator.DividedBy)

        binding.buttonDelete.setOnClickListener(onDeleteClickListener)
        binding.buttonEquals.setOnClickListener(onEqualsClickListener)
    }

    private fun addOperatorBy(button: Button, operator: Operator) {
        button.setOnClickListener {
            presenter.addOperator(operator)
        }
    }

    private fun addOperandBy(button: Button, operand: Int) {
        button.setOnClickListener {
            presenter.addOperation(operand)
        }
    }

    /**
     * Equals Click Listener
     * */
    private val onEqualsClickListener = View.OnClickListener {
        presenter.callEquals()
    }

    /**
     * Equals Click Listener
     * */
    private val onDeleteClickListener = View.OnClickListener {
        presenter.callDelete()
    }

    override fun showResult(result: String) {
        binding.textView.text = result
    }

    override fun showExpression(expression: Expression) {
        binding.textView.text = expression.toString()
    }

    override fun showErrorMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}