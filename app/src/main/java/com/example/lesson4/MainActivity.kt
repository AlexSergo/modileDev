package com.example.lesson4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


interface ActivityCallback{
    fun getNewPerson(person: Person)
}

class MainActivity : AppCompatActivity(), ActivityCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private var fragment: AsyncFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("[APP]", "Create Activity")
        adapter = PersonAdapter(object : IPersonClickListener {
            override fun showPersonName(name: String) {
                Snackbar.make(binding.root, "Нажата карточка: ${name}", 500).show()
            }

            override fun showLikePersonName(name: String) {
                Snackbar.make(binding.root, "Нажат лайк: ${name}", 500).show()
            }

        })

        var fm = supportFragmentManager
        var oldFragment = fm.findFragmentByTag(AsyncFragment.TAG)
        if (oldFragment == null){
            fragment = AsyncFragment()
            Log.i("[APP]", "Create Fragment")
            fm.beginTransaction().add(fragment!!, AsyncFragment.TAG).commit()
        }
        else{
            fragment = oldFragment as AsyncFragment
            Log.i("[APP]", "Find Fragment and get previous messages")
            adapter.getPreviousPersons(fragment!!.persons)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recycleview.layoutManager = layoutManager
        binding.recycleview.adapter = adapter
    }

    override fun getNewPerson(person: Person) {
        Log.i("[APP]", "Get new message")
        adapter.addNewPerson(person)
    }
}