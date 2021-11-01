package com.example.lesson4

import android.os.Bundle
import androidx.fragment.app.Fragment

class AsyncFragment : Fragment() {

    companion object{
        const val TAG = "Async"
    }

    var persons = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        PersonHolder.addListener(personsListener)
    }

    private val personsListener: PersonsListener = {
        if (it != null) {
            persons.add(it)
            val activityCallback = requireActivity() as ActivityCallback
            activityCallback.getNewPerson(it)
        }
    }
}