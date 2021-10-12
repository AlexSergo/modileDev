package com.example.lesson3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3.databinding.Fragment1Binding

class Fragment1 : Fragment(), IFragment {

    lateinit var binding: Fragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = Fragment1Binding.inflate(inflater)
        nextBtnClick()
        return binding.root
    }

    override fun prevBtnClick() {

    }

    override fun nextBtnClick(){
        binding.next.setOnClickListener{
            val activityCallback = requireActivity() as ActivityCallback
            tryToSetOperand(binding.firstOperand.text.toString(), true)
            activityCallback.showNextFragment(this)
        }
    }

    companion object{
        fun newInstance() = Fragment1()
    }

}