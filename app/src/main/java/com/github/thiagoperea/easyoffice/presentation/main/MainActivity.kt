package com.github.thiagoperea.easyoffice.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.thiagoperea.easyoffice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}