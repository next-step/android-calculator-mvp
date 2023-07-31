package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.domain.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val expression by lazy { Expression() }

    private val numberClickListener = View.OnClickListener { v ->
        expression.setOperands((v as Button).text.toString())
        setResultText()
    }

    private val methodClickListener = View.OnClickListener { v ->
        expression.setMethods((v as Button).text.toString())
        setResultText()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {

        binding.button0.setOnClickListener(numberClickListener)
        binding.button1.setOnClickListener(numberClickListener)
        binding.button2.setOnClickListener(numberClickListener)
        binding.button3.setOnClickListener(numberClickListener)
        binding.button4.setOnClickListener(numberClickListener)
        binding.button5.setOnClickListener(numberClickListener)
        binding.button6.setOnClickListener(numberClickListener)
        binding.button7.setOnClickListener(numberClickListener)
        binding.button8.setOnClickListener(numberClickListener)
        binding.button9.setOnClickListener(numberClickListener)

        binding.buttonPlus.setOnClickListener(methodClickListener)
        binding.buttonMinus.setOnClickListener(methodClickListener)
        binding.buttonDivide.setOnClickListener(methodClickListener)

        binding.buttonMultiply.setOnClickListener {
            expression.setMethods("*")
            setResultText()
        }

        binding.buttonDelete.setOnClickListener {
            expression.removeLatestOperands()
            binding.textView.text = expression.getOperands()
        }

        binding.buttonEquals.setOnClickListener {
            onResultEquals()
        }

        binding.buttonMemory.setOnClickListener {
            expression.resetMemory()
            binding.textView.text = expression.getOperands()
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
        binding.textView.text = expression.getOperands()
    }
}
