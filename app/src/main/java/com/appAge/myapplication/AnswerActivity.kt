package com.appAge.myapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.appAge.myapplication.databinding.ActivityAnswerBinding

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
        finish()
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}