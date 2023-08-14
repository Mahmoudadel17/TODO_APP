package com.example.todo.enterySplash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.auth.MainActivity
import com.example.todo.R
import com.example.todo.commonComponents.LottieAnimationView
import com.example.todo.home.HomeActivity
import com.example.todo.ui.theme.TextSplash
import com.example.todo.ui.theme.ToDoTheme


class SplashActivity : ComponentActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    SplashScreen()
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        var intent:Intent

        Handler().postDelayed({
            intent = if (!sharedPreferences.getBoolean("LoginCompleted",false)){
                Intent(this, MainActivity::class.java)
            }else{
                Intent(this, HomeActivity::class.java)

            }
            startActivity(intent)
            finish()

        },4000)
    }


}


@Composable
fun SplashScreen() {

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = stringResource(id = R.string.to_do),
            fontSize = 40.sp,
            color = Color.Black
        )
        Text(
            text = stringResource(id = R.string.splash_text),
            fontSize = 16.sp,
            color = TextSplash
        )
        LottieAnimationView(R.raw.loading,200,250)

    }


}
