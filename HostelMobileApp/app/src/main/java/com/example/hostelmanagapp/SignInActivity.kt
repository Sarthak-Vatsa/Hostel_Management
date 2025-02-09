package com.example.hostelmanagapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hostelmanagapp.ui.theme.HostelManagAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class LoginResponse(
    val message: String = "",
    val rollNo: Long = 0L,
    val role: String = ""
)

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HostelManagAppTheme {
                SignInScreen { rollNo, password -> loginStudent(rollNo, password) }
            }
        }
    }

    private fun loginStudent(rollNo: Long, password: String) {
        val student = Student("", rollNo, password, "", "") // Other fields not needed for login
        RetrofitClient.instance.loginStudent(student)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        runOnUiThread {
                            Toast.makeText(this@SignInActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            // Create intent for DashboardActivity instead of MainActivity
                            val intent = Intent(this@SignInActivity, DashboardActivity::class.java).apply {
                                // Pass the roll number to dashboard
                                putExtra("ROLL_NO", loginResponse?.rollNo ?: rollNo)
                            }
                            startActivity(intent)
                            finish() // Close the SignInActivity so user can't go back to it
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@SignInActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("SignInActivity", "Network call failed", t)
                    runOnUiThread {
                        Toast.makeText(this@SignInActivity, "Failed!!. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }
}

@Composable
fun SignInScreen(onLoginClick: (Long, String) -> Unit) {
    var rollNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign In", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = rollNo,
            onValueChange = { rollNo = it },
            label = { Text("Roll No") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val rollNoLong = rollNo.toLongOrNull() ?: 0L
                if (rollNoLong > 0 && password.isNotBlank()) {
                    onLoginClick(rollNoLong, password)
                }
            }
        ) {
            Text("Sign In")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    HostelManagAppTheme {
        SignInScreen { _, _ ->  }
    }
}