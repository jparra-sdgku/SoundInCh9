package com.example.soundinch9.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    // Private mutable states only this class can change them

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _rememberSession = MutableStateFlow(false)
    private val _emailError = MutableStateFlow(false)
    private val _passwordError = MutableStateFlow(false)

    // Public State The UI can observe these states to get updates only read and not change them
    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val rememberSession: StateFlow<Boolean> = _rememberSession.asStateFlow()
    val emailError: StateFlow<Boolean> = _emailError.asStateFlow()
    val passwordError: StateFlow<Boolean> = _passwordError.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
        _emailError.value = false // clear error when email changes as soon the user types again
    }
    // Password change Challenge

    fun onPasswordChange(password: String) {
        _password.value = password
        _passwordError.value = false
    }

    fun onRememberSessionChange(rememberSession: Boolean) {
        _rememberSession.value = rememberSession
    }

    // Validation --called when the user taps Log In
    fun validateAndLogin(): Boolean {
        val isEmailValid = _email.value.contains('@') && _email.value.contains('.')
        val isPasswordValid = _password.value.length >= 6

        _emailError.value = !isEmailValid
        _passwordError.value = !isPasswordValid

        return isEmailValid && isPasswordValid
    }
}













