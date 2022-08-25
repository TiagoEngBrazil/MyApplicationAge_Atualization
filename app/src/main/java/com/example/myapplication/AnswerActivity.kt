package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityAnswerBinding
import com.example.myapplication.databinding.ActivityMainBinding

class AnswerActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textAnswer1.text = intent.getStringExtra(getString(R.string.recover_text1))
        binding.textAnswer2.text = intent.getStringExtra(getString(R.string.recover_text2))

        binding.buttonGoBack.setOnClickListener(this)

        supportActionBar?.hide()
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        onRestart()
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, MainActivity::class.java))
        onRestart()
    }
}