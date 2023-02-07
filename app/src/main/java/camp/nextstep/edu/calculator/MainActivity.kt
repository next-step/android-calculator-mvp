package camp.nextstep.edu.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.calculator.databinding.ActivityMainBinding
import com.nextstep.edu.calculator.domain.Calculator
import com.nextstep.edu.calculator.domain.OperationsUtil
import com.nextstep.edu.calculator.domain.OperationsUtil.setOperationsOperator
import com.nextstep.edu.calculator.domain.Operator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        binding.buttonDelete.setOnClickListener(setOnOperateClickListener())
        binding.buttonEquals.setOnClickListener(setOnOperateClickListener())
    }

    /**
     * 숫자 버튼에 관한 리스너
     * */
    private fun setOnNumberClickListener() = View.OnClickListener { view ->
        val inputNumberString = (view as Button).text.toString()
        binding.textView.text =
            OperationsUtil.setOperationsNumber(binding.textView.text.toString(), inputNumberString)
    }

    /**
     * 기호 버튼에 관한 리스너
     * */
    private fun setOnOperateClickListener() = View.OnClickListener { view ->
        when (view) {
            binding.buttonDelete ->
                binding.textView.text =
                    OperationsUtil.deleteOperations(binding.textView.text.toString())

            binding.buttonEquals ->
                binding.textView.text = "${Calculator().evaluate(binding.textView.text.toString())}"

            else -> {
                val button = view as Button
                binding.textView.text =
                    setOperationsOperator(binding.textView.text.toString(), button.text.toString())
            }
        }
    }
}