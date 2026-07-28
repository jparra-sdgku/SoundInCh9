package com.example.soundinch9.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserSessionViewModel : ViewModel() {

    // Challenge create the three privates variables for the User Session
    private val _userName = MutableStateFlow("")
    private val _userEmail = MutableStateFlow("")
    private val _isLoggedIn = MutableStateFlow(false)

    // Create the three public variables for the User Session exposed to the UI
    val userName: StateFlow<String> = _userName.asStateFlow()
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

//called when the user logs in
    fun login(name: String, email: String) {
        // Update the User Session variables
        _userName.value = name
        _userEmail.value = email
        _isLoggedIn.value = true
    }
 //called when the user logs out
    fun logout() {
        // Clean the User Session variables
        _userName.value = ""
        _userEmail.value = ""
        _isLoggedIn.value = false
    }
}