package com.example.samojlov_av_homework_module_11_number_5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentViewModel : ViewModel() {
    var result = ""
    var equation = ""
    var outVariable: Byte = 0
    var minus: Byte = 1
    var operatorSymbol = 'f'
    var operator: Byte = 0
    var operatorIsSelected: Byte = 0
    var editTextOut = ""
    val currentEditTextOut: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val currentOperatorIsSelected: MutableLiveData<Byte> by lazy { MutableLiveData<Byte>() }
    val currentOperator: MutableLiveData<Byte> by lazy { MutableLiveData<Byte>() }
    val currentOperatorSymbol: MutableLiveData<Char> by lazy { MutableLiveData<Char>() }
    val currentMinus: MutableLiveData<Byte> by lazy { MutableLiveData<Byte>() }
    val currentOutVariable: MutableLiveData<Byte> by lazy { MutableLiveData<Byte>() }
    val currentEquation: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val currentResult: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}