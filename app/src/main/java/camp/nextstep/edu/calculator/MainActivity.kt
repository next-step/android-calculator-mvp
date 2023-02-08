package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.calculator.domain.Calculator
import com.nextstep.edu.calculator.domain.Expression
import com.nextstep.edu.calculator.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculator: Calculator = Calculator()
    private var expression : Expression = Expression()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonClickListener()
    }

    /**
     * 버튼 Set Click Listener
     * */
    private fun initButtonClickListener() {
        binding.button0.setOnClickListener(setOnNumberClickListener())
        binding.button1.setOnClickListener(setOnNumberClickListener())
        binding.button2.setOnClickListener(setOnNumberClickListener())
        binding.button3.setOnClickListener(setOnNumberClickListener())
        binding.button4.setOnClickListener(setOnNumberClickListener())
        binding.button5.setOnClickListener(setOnNumberClickListener())
        binding.button6.setOnClickListener(setOnNumberClickListener())
        binding.button7.setOnClickListener(setOnNumberClickListener())
        binding.button8.setOnClickListener(setOnNumberClickListener())
        binding.button9.setOnClickListener(setOnNumberClickListener())

        binding.buttonPlus.setOnClickListener(setOnOperateClickListener())
        binding.buttonMinus.setOnClickListener(setOnOperateClickListener())
        binding.buttonMultiply.setOnClickListener(setOnOperateClickListener())
        binding.buttonDivide.setOnClickListener(setOnOperateClickListener())
        binding.buttonDelete.setOnClickListener(setOnDeleteClickListener())
        binding.buttonEquals.setOnClickListener(setOnEqualsClickListener())
    }

    /**
     * Operand Click Listener
     * */
    private fun setOnNumberClickListener() = View.OnClickListener { view ->

        val operand = when (view) {
            binding.button0 -> 0
            binding.button1 -> 1
            binding.button2 -> 2
            binding.button3 -> 3
            binding.button4 -> 4
            binding.button5 -> 5
            binding.button6 -> 6
            binding.button7 -> 7
            binding.button8 -> 8
            binding.button9 -> 9
            else -> throw IllegalArgumentException("Invalid view")
        }

        binding.textView.text =
            expression.addOperand(binding.textView.text.toString()).toString()
    }

    /**
     * Operator Click Listener
     * */
    private fun setOnOperateClickListener() = View.OnClickListener { view ->

        val `operator` = when (view) {
            binding.buttonPlus -> Operator.Plus
            binding.buttonMinus -> Operator.Minus
            binding.buttonMultiply -> Operator.MultiBy
            binding.buttonDivide -> Operator.DividedBy
            else -> throw IllegalArgumentException("Invalid view")
        }

        binding.textView.text =
            addOperator(binding.textView.text.toString(), `operator`)
    }

    /**
     * Delete Click Listener
     * */
    private fun setOnDeleteClickListener() = View.OnClickListener {
        binding.textView.text = Expression.deleteOperations(binding.textView.text.toString())
    }

    /**
     * Equals Click Listener
     * */
    private fun setOnEqualsClickListener() = View.OnClickListener {
        binding.textView.text = "${calculator.evaluate(binding.textView.text.toString())}"
    }
}