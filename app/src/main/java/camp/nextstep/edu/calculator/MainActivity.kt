package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.Calculator
import com.nextstep.edu.domain.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val expression = Expression(calculator = Calculator)

    private val onClickListener = View.OnClickListener { view ->
        when (view) {
            binding.button0 -> expression.setOperands("0")
            binding.button1 -> expression.setOperands("1")
            binding.button2 -> expression.setOperands("2")
            binding.button3 -> expression.setOperands("3")
            binding.button4 -> expression.setOperands("4")
            binding.button5 -> expression.setOperands("5")
            binding.button6 -> expression.setOperands("6")
            binding.button7 -> expression.setOperands("7")
            binding.button8 -> expression.setOperands("8")
            binding.button9 -> expression.setOperands("9")
            binding.buttonPlus -> expression.setMethods("+")
            binding.buttonMinus -> expression.setMethods("-")
            binding.buttonMultiply -> expression.setMethods("*")
            binding.buttonDivide -> expression.setMethods("÷")
            binding.buttonDelete -> expression.removeLatestExpression()
            binding.buttonMemory -> expression.resetMemory()
        }
        setResultText()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {

        binding.button0.setOnClickListener(onClickListener)
        binding.button1.setOnClickListener(onClickListener)
        binding.button2.setOnClickListener(onClickListener)
        binding.button3.setOnClickListener(onClickListener)
        binding.button4.setOnClickListener(onClickListener)
        binding.button5.setOnClickListener(onClickListener)
        binding.button6.setOnClickListener(onClickListener)
        binding.button7.setOnClickListener(onClickListener)
        binding.button8.setOnClickListener(onClickListener)
        binding.button9.setOnClickListener(onClickListener)

        binding.buttonPlus.setOnClickListener(onClickListener)
        binding.buttonMinus.setOnClickListener(onClickListener)
        binding.buttonDivide.setOnClickListener(onClickListener)
        binding.buttonMultiply.setOnClickListener(onClickListener)

        binding.buttonDelete.setOnClickListener(onClickListener)
        binding.buttonMemory.setOnClickListener(onClickListener)

        binding.buttonEquals.setOnClickListener {
            onResultEquals()
        }
    }

    private fun onResultEquals() {
        runCatching {
            expression.resultCalculate()
        }.onSuccess {
            setResultText()
        }.onFailure {
            Toast.makeText(this@MainActivity, getString(R.string.incomplete_expression), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResultText() {
        binding.textView.text = expression.expressions
    }
}
