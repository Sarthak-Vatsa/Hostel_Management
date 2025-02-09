package com.example.hostelmanagapp;

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hostelmanagapp.ui.theme.HostelManagAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class ComplaintResponse(
    val message: String
)

data class Complaint(
    val type: String,
    val mobNo: Long
)

class ComplaintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rollNo = intent.getLongExtra("ROLL_NO", 0L)

        setContent {
            HostelManagAppTheme {
                ComplaintScreen(rollNo)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplaintScreen(rollNo: Long) {
    var content by remember { mutableStateOf("") }
    var mobileNo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register Complaint") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Complaint Content") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = mobileNo,
                onValueChange = { mobileNo = it },
                label = { Text("Mobile Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (content.isBlank() || mobileNo.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val mobileNumber = mobileNo.toLongOrNull()
                    if (mobileNumber == null) {
                        Toast.makeText(context, "Invalid mobile number", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val complaintRequest = Complaint(
                        type = content,
                        mobNo = mobileNumber
                    )

                    RetrofitClient.instance.registerComplaint(complaintRequest)
                        .enqueue(object : Callback<ComplaintResponse> {
                            override fun onResponse(
                                call: Call<ComplaintResponse>,
                                response: Response<ComplaintResponse>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Complaint Registered Successfully", Toast.LENGTH_SHORT).show()
                                    (context as? ComponentActivity)?.finish()
                                } else {
                                    Toast.makeText(context, "Failed to register complaint", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<ComplaintResponse>, t: Throwable) {
                                Toast.makeText(context, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Complaint")
            }
        }
    }
}