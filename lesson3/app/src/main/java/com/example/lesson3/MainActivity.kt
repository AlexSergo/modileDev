package com.example.lesson3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.lesson3.databinding.ActivityMainBinding

interface ActivityCallback{
    fun showNextFragment(fragment:androidx.fragment.app.Fragment)
    fun showPreviousFragment(fragment:androidx.fragment.app.Fragment)
}

class MainActivity : AppCompatActivity(), ActivityCallback {

    lateinit var binding: ActivityMainBinding
    var fragmentsList: List<Fragment> =
        listOf(Fragment1.newInstance(), Fragment2.newInstance(), Fragment3.newInstance(),
            Fragment4.newInstance())
    var buttonsList: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonsList.add(0, binding.fr1)
        buttonsList.add(1, binding.fr2)
        buttonsList.add(2, binding.fr3)
        buttonsList.add(3, binding.fr4)
        setClickListenerForButtons()
    }

    private fun setClickListenerForButtons(){
        buttonsList[0].setOnClickListener{
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[0]).commit()
            buttonsManager(0)
        }
        buttonsList[1].setOnClickListener{
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[1]).commit()
            buttonsManager(1)
        }
        buttonsList[2].setOnClickListener{
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[2]).commit()
            buttonsManager(2)
        }
        buttonsList[3].setOnClickListener{
            supportFragmentManager
                .beginTransaction().replace(R.id.container, fragmentsList[3]).commit()
            buttonsManager(3)
        }

        supportFragmentManager
            .beginTransaction().replace(R.id.container, fragmentsList[0]).commit()
        buttonsManager(0)
    }

    fun showFragment(prevFragment: Fragment, nextFragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .hide(prevFragment).commit()

        supportFragmentManager
            .beginTransaction().replace(R.id.container, nextFragment).commit()
    }

    override fun showNextFragment(fragment:Fragment) {
        val nextFragment = findNextFragment(fragment.id, 1)

        showFragment(fragment, nextFragment!!)
    }

    override fun showPreviousFragment(fragment: Fragment) {
        val prevFragment = findNextFragment(fragment.id, -1)

        showFragment(fragment, prevFragment!!)
    }

    fun findNextFragment(id: Int, inc: Int): Fragment? {
        val n = fragmentsList.count() - 1
        for (i in 0..n)
            if (fragmentsList[i].id == id) {
                buttonsManager(i + inc)
                return fragmentsList[i + inc]
            }
        return null
    }

    private fun buttonsManager(n: Int) {
        for (i in 0 until buttonsList.count())
            buttonsList[i].isEnabled = i <= n
    }
}