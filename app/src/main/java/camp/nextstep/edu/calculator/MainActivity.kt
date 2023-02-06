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

        binding.apply {
            initButtonClickListener()
        }
    }

    /**
     * 버튼 Set Click Listener
     * */
    private fun ActivityMainBinding.initButtonClickListener() {
        button0.setOnClickListener(setOnNumberClickListener())
        button1.setOnClickListener(setOnNumberClickListener())
        button2.setOnClickListener(setOnNumberClickListener())
        button3.setOnClickListener(setOnNumberClickListener())
        button4.setOnClickListener(setOnNumberClickListener())
        button5.setOnClickListener(setOnNumberClickListener())
        button6.setOnClickListener(setOnNumberClickListener())
        button7.setOnClickListener(setOnNumberClickListener())
        button8.setOnClickListener(setOnNumberClickListener())
        button9.setOnClickListener(setOnNumberClickListener())

        buttonPlus.setOnClickListener(setOnOperateClickListener())
        buttonMinus.setOnClickListener(setOnOperateClickListener())
        buttonMultiply.setOnClickListener(setOnOperateClickListener())
        buttonDivide.setOnClickListener(setOnOperateClickListener())
        buttonDelete.setOnClickListener(setOnOperateClickListener())
        buttonEquals.setOnClickListener(setOnOperateClickListener())
    }

    /**
     * 숫자 버튼에 관한 리스너
     * */
    private fun ActivityMainBinding.setOnNumberClickListener() = View.OnClickListener { view ->
        val inputNumberString = (view as Button).text.toString()
        textView.text = OperationsUtil.setOperationsNumber(textView.text.toString(), inputNumberString)
    }

    /**
     * 기호 버튼에 관한 리스너
     * */
    private fun ActivityMainBinding.setOnOperateClickListener() = View.OnClickListener { view ->
        when (view) {
            buttonDelete ->
                textView.text = OperationsUtil.deleteOperations(textView.text.toString())

            buttonEquals ->
                textView.text = "${Calculator().evaluate(textView.text.toString())}"

            else -> {
                val button = view as Button
                textView.text = setOperationsOperator(textView.text.toString(), button.text.toString())
            }
        }
    }


}
