package com.github.thiagoperea.easyoffice.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.thiagoperea.easyoffice.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}