package com.example.lesson3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson3.databinding.Fragment2Binding
import com.example.lesson3.databinding.Fragment3Binding


class Fragment3 : Fragment(), IFragment {

    lateinit var binding: Fragment3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(inflater)
        nextBtnClick()
        prevBtnClick()
        return binding.root
    }

    override fun nextBtnClick(){
        binding.next.setOnClickListener{
            val activityCallback = requireActivity() as ActivityCallback
            Calculator.operation = binding.operation.text.toString()
            activityCallback.showNextFragment(this)
        }
    }

    override fun prevBtnClick() {
        binding.prev.setOnClickListener{
            val activityCallback = requireActivity() as ActivityCallback
            Calculator.operation = binding.operation.text.toString()
            activityCallback.showPreviousFragment(this)
        }
    }

    companion object {
        fun newInstance() = Fragment3()
    }
}