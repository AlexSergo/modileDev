package com.example.lesson3

import java.lang.Exception

interface IFragment {
    fun prevBtnClick()
    fun nextBtnClick()
    fun tryToSetOperand(operand: String, isFirst:Boolean){
        if (!isFirst)
            try {
                Calculator.secondOperand = operand.toDouble()
            } catch (e: Exception) {
                Calculator.secondOperand = null
            }
        else
            try {
                Calculator.firstOperand= operand.toDouble()
            } catch (e: Exception) {
                Calculator.firstOperand = null
            }
    }
}