package com.example.hostelmanagapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.example.hostelmanagapp.ui.theme.HostelManagAppTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HostelManagAppTheme {
                MainScreen(
                  onSignUpClick = { navigateToRegister() },
                    onSignInClick = { navigateToSignIn() }
                )
            }
        }
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun navigateToSignIn() {
        startActivity(Intent(this, SignInActivity::class.java))
    }
}

@Composable
fun MainScreen(onSignUpClick: () -> Unit, onSignInClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onSignUpClick) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick =  onSignInClick ) {
            Text("Sign In")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HostelManagAppTheme {
        MainScreen(
            onSignUpClick = {},
            onSignInClick = {}
        )
    }
}

