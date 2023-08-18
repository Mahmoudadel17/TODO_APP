package com.example.todo.auth



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.todo.R



@Composable
fun TextFieldColors() : TextFieldColors{
    return  OutlinedTextFieldDefaults.colors(
        // text color
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.primary,
        errorTextColor = MaterialTheme.colorScheme.primary,
        // label color
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
        errorLabelColor = Color.Red,

        cursorColor = MaterialTheme.colorScheme.primary,
        errorBorderColor = Color.Red,
        // icon color
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
    )

}

@Composable
fun CheckBox(text:String,checkBox:Boolean,onCheckedChange:() -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checkBox, onCheckedChange = {
            onCheckedChange()
        })
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text,
            color = Color.DarkGray,
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}









@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameEditText(
    userName:String ,
    isUsernameError:Boolean ,
    userNameErrorMessage:String,
    onValueChange:(String) -> Unit) {
    OutlinedTextField(
        label = { Text("UserName",style = TextStyle(color = Color.DarkGray)) },
        value = userName,
        onValueChange = {
            onValueChange(it)
        },
        colors = TextFieldColors(),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        isError = isUsernameError,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "")
        }
    )
    Row {
        Text(
            userNameErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailEditText(
    email:String ,
    isErrorEmail:Boolean ,
    emailErrorMessage:String,
    onValueChange:(String) -> Unit) {
    OutlinedTextField(
        label = { Text("Email") },
        value = email,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = {
            onValueChange(it)

        },
        colors = TextFieldColors(),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            ,
        isError = isErrorEmail,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "email icon")
        }
    )
    Row {
        Text(
            emailErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordEditText(
    password:String ,
    isErrorPassword:Boolean ,
    passwordErrorMessage:String ,
    showPassword:Boolean,
    onValueChange:(String) -> Unit,
    onIconButtonClick:() -> Unit
) {
    OutlinedTextField(
        label = { Text("Password") },
        value = password,
        isError = isErrorPassword,
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = {
            onValueChange(it)
        },
        colors =  TextFieldColors(),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {
                onIconButtonClick()
            }) {
                Icon(painter = painterResource(id = if(showPassword) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                    contentDescription = "password icon")
            }
        }
    )
    Row {
        Text(
            passwordErrorMessage, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .padding(top = 3.dp, start = 25.dp), color = Color.Red
        )
        Spacer(modifier = Modifier.weight(1f))

    }

}


@Composable
fun ButtonClickOn(buttonText:String,paddingValue:Int ,onButtonClick:() -> Unit ) {
    Button (colors = ButtonDefaults.buttonColors(containerColor =MaterialTheme.colorScheme.primary),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = Modifier
            .padding(top =paddingValue.dp)
            .fillMaxWidth(1f)

    ){
        Text(text = buttonText, fontSize = 30.sp, style = TextStyle(color = MaterialTheme.colorScheme.background))
    }
}