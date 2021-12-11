package view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication6.R
import com.example.myapplication6.databinding.ActivityRelationshipsBinding
import utils.ID

enum class NodeType{
    Parent,
    Child
}

class RelationshipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRelationshipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelationshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.extras?.get(ID).toString().toInt()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RelationshipsFragment.newInstance(id, NodeType.Parent))
            .commit()

        binding.parentButton.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RelationshipsFragment.newInstance(id, NodeType.Parent))
                .commit()
        }

        binding.childButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RelationshipsFragment.newInstance(id, NodeType.Child))
                .commit()
        }

        binding.backButton.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}