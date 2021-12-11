package view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication6.R
import com.example.myapplication6.databinding.MainActivityBinding
import utils.NEW_NODE
import utils.SAVE_VALUE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var fragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fragment = MainFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }

        binding.newNodeButton.setOnClickListener {
            val intent = Intent(this, NewNodeActivity::class.java)
            startActivityForResult(intent, NEW_NODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NODE && resultCode == SAVE_VALUE){
            data?.let {
                fragment.addNewNode(it)
            }
        }
    }
}