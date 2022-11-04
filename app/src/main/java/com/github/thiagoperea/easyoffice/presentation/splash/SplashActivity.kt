package com.github.thiagoperea.easyoffice.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.thiagoperea.easyoffice.databinding.ActivitySplashBinding
import com.github.thiagoperea.easyoffice.presentation.login.LoginActivity
import com.github.thiagoperea.easyoffice.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        loadResources()
    }

    private fun loadResources() {
        // do whatever you need to do before the app starts
        viewModel.loadResources()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {

            viewModel.splashState.collect { newState ->
                when (newState) {
                    SplashState.Loading -> showLoading()
                    SplashState.UserNotLogged -> goToLoginScreen()
                    SplashState.UserLoggedIn -> goToMainScreen()
                }
            }
        }
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}