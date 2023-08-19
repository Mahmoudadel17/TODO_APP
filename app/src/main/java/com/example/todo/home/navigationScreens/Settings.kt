package com.example.todo.home.navigationScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo.R


@Composable
fun SettingsScreen(bottomNavController: NavHostController) {
    Column(modifier = Modifier.padding(5.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 50.dp)
        ){
            Image(
                painter =
                painterResource(id = R.drawable.avatar1),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)

            )
            Text(
                text = "Mahmoud Adel",
                fontSize = 25.sp,
                color = Color.DarkGray
            )
            Text(
                text = "mahmoudadel5556@gmail.com",
                fontSize = 15.sp,
                color = Color.DarkGray,
            )

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 50.dp, start = 5.dp)
                .clickable {

                }
        ) {
            Icon(
                imageVector = Icons.Default.Lock ,
                contentDescription = "Reset Password",
                tint = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text="Reset Password",
                fontSize = 20.sp,
                color = Color.DarkGray,)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 20.dp, start = 5.dp)
                .clickable {

                }
        ){
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Log out",
                tint = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text="Log out", fontSize = 20.sp,
                color = Color.DarkGray,)
        }
    }
}





