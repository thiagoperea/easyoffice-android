package com.github.thiagoperea.easyoffice.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object InitialState : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    object Failure : LoginState()
}

class LoginViewModel : ViewModel() {

    val loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.InitialState)

    fun doLogin(login: String, password: String) {

        viewModelScope.launch {
            loginState.emit(LoginState.Loading)

            delay(1000) // mock

            loginState.emit(LoginState.Success)

//            if (login == "juninho" && password == "123") {
//            } else {
//                loginState.emit(LoginState.Failure)
//            }
        }
    }
}
