package com.example.todo.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo.R
import com.example.todo.commonComponents.LottieAnimationShow
import com.example.todo.commonComponents.Screens


@Composable
fun SignUpScreen(navController: NavController) {

    val viewModel:SignUpViewModel = viewModel()
    val userName by viewModel.userName.collectAsState()
    val isErrorUserName by viewModel.isErrorUserName.collectAsState()
    val userNameErrorMessage by viewModel.userNameErrorMessage.collectAsState()

    val email by viewModel.email.collectAsState()
    val isErrorEmail by viewModel.isErrorEmail.collectAsState()
    val emailErrorMessage by viewModel.emailErrorMessage.collectAsState()
    val password by viewModel.password.collectAsState()
    val isErrorPassword by viewModel.isErrorPassword.collectAsState()
    val passwordErrorMessage by viewModel.passwordErrorMessage.collectAsState()
    val showPassword by viewModel.showPassword.collectAsState()
    val checkBox by viewModel.checkBox.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
    ) {
        LottieAnimationShow(R.raw.signup,200,0)
        UserNameEditText(
            userName = userName,
            isUsernameError = isErrorEmail,
            userNameErrorMessage = userNameErrorMessage,
            onValueChange = {viewModel.onUserNameChange(it)}
        )
        EmailEditText(
            email = email,
            isErrorEmail = isErrorEmail,
            emailErrorMessage = emailErrorMessage,
            onValueChange = {viewModel.onEmailChange(it)}
        )
        PasswordEditText(
            password = password,
            isErrorPassword = isErrorPassword,
            passwordErrorMessage = passwordErrorMessage,
            showPassword = showPassword,
            onValueChange = {viewModel.onPasswordChange(it)}
        ) {
            viewModel.onIconButtonClick()
        }
        CheckBox(text = "Remember Me", checkBox = checkBox) {
            viewModel.onCheckBoxClick()
        }
        ButtonClickOn(buttonText = "Login", paddingValue = 40) {
            viewModel.onSignUpButtonClick(navController)
        }
        Row{
            Text(
                text ="You already registered?",
                modifier = Modifier
                    .padding(top = 3.dp, start = 25.dp),
                color = Color.DarkGray,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                "Login",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .clickable {
                        navController.navigate(Screens.LoginScreen.route){
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                color = Color.DarkGray,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.weight(1f))

        }


    }
}