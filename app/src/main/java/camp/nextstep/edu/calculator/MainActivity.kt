package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import camp.nextstep.edu.domain.Calculator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val calculator by lazy { Calculator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnNumberClickListener()
        setOnBasicOperationClickListener()
        setOnExecuteClickListener()
        setOnDeleteExpressionClickListener()
    }

    private fun setOnNumberClickListener() {
        with(binding) {
            val numberButtons = listOf(
                button0,
                button1,
                button2,
                button3,
                button4,
                button5,
                button6,
                button7,
                button8,
                button9
            )

            numberButtons.forEachIndexed { index, button ->
                button.setOnClickListener {
                    calculator.addNumberOnExpressions(index)
                    setExpressions()
                }
            }
        }
    }

    private fun setOnBasicOperationClickListener() {
        with(binding) {
            buttonPlus.setOnClickListener {
                calculator.addPlusOnExpression()
                setExpressions()
            }
            buttonMinus.setOnClickListener {
                calculator.addMinusOnExpression()
                setExpressions()
            }
            buttonDivide.setOnClickListener {
                calculator.addDivideOnExpression()
                setExpressions()
            }
            buttonMultiply.setOnClickListener {
                calculator.addMultiplyOnExpression()
                setExpressions()
            }
        }
    }

    private fun setOnExecuteClickListener() {
        binding.buttonEquals.setOnClickListener {
            calculator.execute()
            setExpressions()
        }
    }

    private fun setOnDeleteExpressionClickListener() {
        binding.buttonDelete.setOnClickListener {
            calculator.deleteExpression()
            setExpressions()
        }
    }

    private fun setExpressions() {
        binding.textView.text = calculator.getExpressions()
    }
}
