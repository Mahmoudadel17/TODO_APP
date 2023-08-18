package com.example.todo.auth

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel:LoginScreenViewModel() {
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _isErrorUserName = MutableStateFlow(false)
    val isErrorUserName: StateFlow<Boolean> = _isErrorUserName.asStateFlow()

    private val _userNameErrorMessage = MutableStateFlow("")
    val userNameErrorMessage: StateFlow<String> = _userNameErrorMessage.asStateFlow()




    fun onUserNameChange(userName:String){
        val newUserName = userName
        this._userName.value = newUserName

        // Reset error if found
        _isErrorUserName.value = false
        _userNameErrorMessage.value = ""
    }

    fun onSignUpButtonClick(navController: NavController) {

    }

}