package com.example.lesson3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3.databinding.Fragment2Binding
import java.lang.Exception

class Fragment2 : Fragment(), IFragment {

    lateinit var binding: Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = Fragment2Binding.inflate(inflater)
        nextBtnClick()
        prevBtnClick()
        return binding.root
    }

    override fun prevBtnClick() {
        binding.prev.setOnClickListener{
            val activityCallback = requireActivity() as ActivityCallback
            tryToSetOperand(binding.secondOperand.text.toString(), false)
            activityCallback.showPreviousFragment(this)
        }
    }

    override fun nextBtnClick(){
        binding.next.setOnClickListener{
            val activityCallback = requireActivity() as ActivityCallback
            tryToSetOperand(binding.secondOperand.text.toString(), false)
            activityCallback.showNextFragment(this)
        }
    }


    companion object{
        @JvmStatic
        fun newInstance() = Fragment2()
    }
}