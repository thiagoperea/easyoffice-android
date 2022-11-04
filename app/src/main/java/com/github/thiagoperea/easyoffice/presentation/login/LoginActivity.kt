package com.github.thiagoperea.easyoffice.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.thiagoperea.easyoffice.databinding.ActivityLoginBinding
import com.github.thiagoperea.easyoffice.presentation.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        lifecycleScope.launchWhenCreated {

            viewModel.loginState.collect { newState ->
                when (newState) {
                    LoginState.InitialState -> {}
                    LoginState.Loading -> showLoading()
                    LoginState.Success -> goToMainScreen()
                    LoginState.Failure -> showErrorMessage()
                }
            }
        }
    }

    private fun setupListeners() {
        binding.buttonLogin.setOnClickListener {
            val login = binding.layoutLogin.editText?.text.toString()
            val password = binding.layoutPassword.editText?.text.toString()

            viewModel.doLogin(login, password)
        }
    }

    private fun showLoading() {
        binding.progressIndicator.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressIndicator.visibility = View.INVISIBLE
    }

    private fun goToMainScreen() {
        hideLoading()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorMessage() {
        hideLoading()
        Toast.makeText(this, "Usuário ou senha invalida", Toast.LENGTH_SHORT).show()
    }
}