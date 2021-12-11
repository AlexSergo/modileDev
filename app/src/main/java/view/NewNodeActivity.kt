package view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.myapplication6.R
import com.example.myapplication6.databinding.ActivityNewNodeBinding
import utils.ERROR
import utils.KEY_VALUE
import utils.SAVE_VALUE

class NewNodeActivity : AppCompatActivity() {

    private lateinit var nodeEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var binding: ActivityNewNodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nodeEditText = binding.editTextNumber
        saveButton = binding.submitButton

        saveButton.setOnClickListener{
            val intent = Intent()
            if (TextUtils.isEmpty(nodeEditText.text))
                setResult(ERROR, intent)
            else{
                intent.putExtra(KEY_VALUE, nodeEditText.text.toString())
                setResult(SAVE_VALUE,intent)
            }
            finish()
        }
    }
}