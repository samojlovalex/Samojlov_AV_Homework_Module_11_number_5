package com.example.samojlov_av_homework_module_11_number_5

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.samojlov_av_homework_module_11_number_5.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbarMain: androidx.appcompat.widget.Toolbar
    private lateinit var editTextET: EditText
    private lateinit var textVieWTV: TextView
    private lateinit var buttonOneBT: Button
    private lateinit var buttonTwoBT: Button
    private lateinit var buttonThreeBT: Button
    private lateinit var buttonFourBT: Button
    private lateinit var buttonFiveBT: Button
    private lateinit var buttonSixBT: Button
    private lateinit var buttonSevenBT: Button
    private lateinit var buttonEightBT: Button
    private lateinit var buttonNineBT: Button
    private lateinit var buttonZeroBT: Button
    private lateinit var buttonDivisionBT: Button
    private lateinit var buttonMultiplicationBT: Button
    private lateinit var buttonMinusBT: Button
    private lateinit var buttonPlusBT: Button
    private lateinit var buttonEquallyBT: Button
    private lateinit var buttonResetBT: Button
    private var outVariable: Byte = 0 //Если 0 - нельзя вводить операторы и 0
    private var minus: Byte = 1 // Если 0 - нельзя вводить "-"
    private var operatorSymbol = 'f' //Переменная для приема символа оператора
    private var operator: Byte = 0 //Переменная для защиты от ввода двух операторов подряд
    private var operatorIsSelected: Byte = 0 // Если 0 - оператор не выбран
    private var editTextOut = ""
    private var calculatorStroke = ""
    lateinit var currentViewModel: CurrentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    private fun init() {
        toolbarMain = binding.toolbarMain
        setSupportActionBar(toolbarMain)
        title = getString(R.string.toolbar_title)
        toolbarMain.subtitle = getString(R.string.toolbar_subtitle)
        toolbarMain.setLogo(R.drawable.calculate_icon)

        editTextET = binding.editTextET
        textVieWTV = binding.textVieWTV
        buttonOneBT = binding.buttonOneBT
        buttonTwoBT = binding.buttonTwoBT
        buttonThreeBT = binding.buttonThreeBT
        buttonFourBT = binding.buttonFourBT
        buttonFiveBT = binding.buttonFiveBT
        buttonSixBT = binding.buttonSixBT
        buttonSevenBT = binding.buttonSevenBT
        buttonEightBT = binding.buttonEightBT
        buttonNineBT = binding.buttonNineBT
        buttonZeroBT = binding.buttonZeroBT
        buttonDivisionBT = binding.buttonDivisionBT
        buttonMultiplicationBT = binding.buttonMultiplicationBT
        buttonMinusBT = binding.buttonMinusBT
        buttonPlusBT = binding.buttonPlusBT
        buttonEquallyBT = binding.buttonEquallyBT
        buttonResetBT = binding.buttonResetBT


        editTextET.isEnabled = false
        editTextET.setText(this.editTextOut)

        buttonOneBT.setOnClickListener(this)
        buttonTwoBT.setOnClickListener(this)
        buttonThreeBT.setOnClickListener(this)
        buttonFourBT.setOnClickListener(this)
        buttonFiveBT.setOnClickListener(this)
        buttonSixBT.setOnClickListener(this)
        buttonSevenBT.setOnClickListener(this)
        buttonEightBT.setOnClickListener(this)
        buttonNineBT.setOnClickListener(this)
        buttonZeroBT.setOnClickListener(this)
        buttonMinusBT.setOnClickListener(this)
        buttonPlusBT.setOnClickListener(this)
        buttonDivisionBT.setOnClickListener(this)
        buttonMultiplicationBT.setOnClickListener(this)
        buttonEquallyBT.setOnClickListener(this)

        buttonResetBT.setOnClickListener {
            //Кнопка reset
            reset()
        }

        buttonEquallyBT.setOnClickListener {
            //Кнопка "=" и вывод результата
            equally()
        }

        saveDataMainActivity()
        //Сохраниение данных при смене lifecycle приложения

    }

    private fun saveDataMainActivity() {
        currentViewModel = ViewModelProvider(this)[CurrentViewModel::class.java]

        currentViewModel.currentResult.observe(this) {
            textVieWTV.text = it
        }

        currentViewModel.currentEquation.observe(this) {
            editTextET.setText(it)
        }

        currentViewModel.currentEditTextOut.observe(this) {
            editTextOut = it
        }

        currentViewModel.currentOutVariable.observe(this) {
            outVariable = it
        }

        currentViewModel.currentMinus.observe(this) {
            minus = it
        }

        currentViewModel.currentOperatorSymbol.observe(this) {
            operatorSymbol = it
        }

        currentViewModel.currentOperator.observe(this) {
            operator = it
        }

        currentViewModel.currentOperatorIsSelected.observe(this) {
            operatorIsSelected = it
        }
    }

    @SuppressLint("SetTextI18n")
    private fun equally() {
        if (textVieWTV.text.isNotEmpty()) {
            return
        }
        calculatorStroke = resultCalculator(editTextOut)
        editTextOut = "$editTextOut ="
        editTextET.setText(editTextOut)
        textVieWTV.text = calculatorStroke
        currentViewModel.currentEquation.value =
            (editTextOut.also { currentViewModel.equation = it })

        currentViewModel.currentResult.value =
            (calculatorStroke.also { currentViewModel.result = it })

    }

    private fun reset() {
        textVieWTV.text = ""
        editTextET.text.clear()
        textVieWTV.text = ""
        editTextOut = ""
        editTextET.text.clear()
        outVariable = 0
        minus = 1
        operatorSymbol = 'f'
        operator = 0
        operatorIsSelected = 0
        calculatorStroke = ""

        currentViewModel.currentEditTextOut.value =
            (editTextOut.also { currentViewModel.editTextOut = it })

        (editTextOut.also { currentViewModel.equation = it })

        currentViewModel.currentResult.value =
            (calculatorStroke.also { currentViewModel.result = it })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.toast_exit),
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        if (textVieWTV.text.isNotEmpty()) {
            return
        }

        val result = keyboardSymbol(v)

        editTextStroke(result)

        editTextET.setText(editTextOut)

        currentViewModel.currentOutVariable.value =
            (outVariable.also { currentViewModel.outVariable = it })

        currentViewModel.currentMinus.value = (minus.also { currentViewModel.minus = it })

        currentViewModel.currentOperatorSymbol.value =
            (operatorSymbol.also { currentViewModel.operatorSymbol = it })

        currentViewModel.currentOperator.value = (operator.also { currentViewModel.operator = it })

        currentViewModel.currentOperatorIsSelected.value =
            (operatorIsSelected.also { currentViewModel.operatorIsSelected = it })
    }

    private fun checkingOperator(variable: Char): Char {
        //Оператор можно вводить только после символов чисел
        val variableOut: Char
        if (outVariable == 1.toByte() && operator == 1.toByte()) {
            outVariable = 0
            operator = 0
            operatorIsSelected = 1
            minus = 1
            variableOut = variable
        } else variableOut = 'f'
        return variableOut
    }

    private fun checkingMinus(): Char {
        //Минус можно вводить везде, в том числе и в начале уравнения, но не подряд
        val outVariableCheck: Char
        if (minus == 0.toByte()) {
            outVariableCheck = 'f'
        } else {
            outVariableCheck = '-'
            minus = 0
            operator = 0
        }
        return outVariableCheck
    }

    private fun checkingZero(): Char {
        val outChar = if (operator == 0.toByte()) 'f'
        else '0'
        return outChar
    }

    private fun choosingAChange(char: Char): Char {
        //После ввода символа числа можно вводить символы операторов и 0
        outVariable = 1
        minus = 1
        operator = 1
        return char
    }

    private fun keyboardSymbol(v: View?): Char {
        //Клавиатура, кроме клавишы reset
        val variable = when (v?.id) {
            R.id.buttonOneBT -> choosingAChange('1')
            R.id.buttonTwoBT -> choosingAChange('2')
            R.id.buttonThreeBT -> choosingAChange('3')
            R.id.buttonFourBT -> choosingAChange('4')
            R.id.buttonFiveBT -> choosingAChange('5')
            R.id.buttonSixBT -> choosingAChange('6')
            R.id.buttonSevenBT -> choosingAChange('7')
            R.id.buttonEightBT -> choosingAChange('8')
            R.id.buttonNineBT -> choosingAChange('9')
            R.id.buttonZeroBT -> checkingZero()
            R.id.buttonDivisionBT -> checkingOperator('/')
            R.id.buttonMultiplicationBT -> checkingOperator('*')
            R.id.buttonMinusBT -> checkingMinus()
            R.id.buttonPlusBT -> checkingOperator('+')
            else -> 'f'
        }
        return variable
    }


    private fun editTextStroke(char: Char) {

        if (operatorIsSelected == 1.toByte() && char != 'f') {
            operatorSymbol = char
            editTextOut = "$editTextOut$operatorSymbol"
        }

        if (char != 'f' && operatorSymbol == 'f') {
            editTextOut = "$editTextOut$char"
            operatorSymbol = 'f'
            operatorIsSelected = 0
        }
        currentViewModel.currentEditTextOut.value =
            (editTextOut.also { currentViewModel.editTextOut = it })

    }

    private fun resultCalculator(stroke: String): String {
        //Использование библиотеки exp4j для математических расчетов из строки
        var result: String
        try {
            val expr = ExpressionBuilder(stroke).build()
            //строим выражение
            val res = expr.evaluate()
            //Находим ответ (число, может быть нецелое)
            val longRes = res.toLong()
            //longres - число в формате long (целочисленное)
            result = if (longRes.toDouble() == res) {
                //Если число целое,
                longRes.toString()
                //То: Отбрасываем ноль после запятой
            } else {
                res.toString()
                //Иначе: Сохраняем числа после запятой
            }
        } catch (e: Exception) {
            //Если выражение записано некорректно
            result = "Error"
            //В поле ответа пишем 'Error'
        }
        return result
    }
}