package com.github.thiagoperea.easyoffice.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    val splashState: MutableStateFlow<SplashState> = MutableStateFlow(SplashState.Loading)

    fun loadResources() {
        viewModelScope.launch {
            delay(2000)

            val isUserLoggedIn = false

            if (isUserLoggedIn) {
                splashState.emit(SplashState.UserLoggedIn)
            } else {
                splashState.emit(SplashState.UserNotLogged)
            }
        }
    }
}

sealed class SplashState {
    object Loading : SplashState()
    object UserLoggedIn : SplashState()
    object UserNotLogged : SplashState()
}