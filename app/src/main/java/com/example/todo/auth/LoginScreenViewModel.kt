package com.example.todo.auth

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.todo.commonComponents.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class LoginScreenViewModel:ViewModel() {
    // Email related properties
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _isErrorEmail = MutableStateFlow(false)
    val isErrorEmail: StateFlow<Boolean> = _isErrorEmail.asStateFlow()

    private val _emailErrorMessage = MutableStateFlow("")
    val emailErrorMessage: StateFlow<String> = _emailErrorMessage.asStateFlow()

    // Password related properties
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _isErrorPassword = MutableStateFlow(false)
    val isErrorPassword: StateFlow<Boolean> = _isErrorPassword.asStateFlow()

    private val _passwordErrorMessage = MutableStateFlow("")
    val passwordErrorMessage: StateFlow<String> = _passwordErrorMessage.asStateFlow()

    private val _showPassword = MutableStateFlow(false)
    val showPassword: StateFlow<Boolean> = _showPassword.asStateFlow()

    private val _checkBox = MutableStateFlow(false)
    val checkBox: StateFlow<Boolean> = _checkBox.asStateFlow()


    fun onEmailChange(email:String){
        val newEmail = email
        this._email.value = newEmail

        // Reset error if found
        _isErrorEmail.value = false
        _emailErrorMessage.value = ""
    }

    fun onPasswordChange(password:String){
        val newPassword = password
        this._password.value = newPassword

        // Reset error if found
        _isErrorPassword.value = false
        _passwordErrorMessage.value = ""

    }

    fun onCheckBoxClick(){
        val newCheck = _checkBox.value.not()
        _checkBox.value = newCheck
    }
    fun onIconButtonClick(){
        val newShowPassword = _showPassword.value.not()
        _showPassword.value = newShowPassword
    }
    fun onLoginButtonClick(navController: NavController) {
       navController.navigate(Screens.HomeRoute.route)
    }

    private fun isValidEmail(email: String): Boolean {
        // Regular expression pattern to validate the email format
        val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        // Check if the provided email matches the pattern
        return email.matches(Regex(pattern))
    }

}


