package com.example.hostelmanagapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hostelmanagapp.ui.theme.HostelManagAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rollNo = intent.getLongExtra("ROLL_NO", 0L)

        setContent {
            HostelManagAppTheme {
                DashboardScreen(rollNo)
            }
        }
    }
}

data class LogoutResponse(
    val message: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(rollNo: Long) {
    val context = LocalContext.current
    var showComplaintDialog by remember { mutableStateOf(false) }
    var notices by remember { mutableStateOf<List<Notice>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        RetrofitClient.instance.getNotices().enqueue(object : Callback<List<Notice>> {
            override fun onResponse(call: Call<List<Notice>>, response: Response<List<Notice>>) {
                if (response.isSuccessful) {
                    Log.i("Notices", response.body().toString())
                    notices = response.body() ?: emptyList()
                } else {
                        Log.e("API Error", """
                Status Code: ${response.code()}
                Error Body: ${response.errorBody()?.string()}
                Raw Response: ${response.raw()}
            """.trimIndent())
                    error = "Failed to load notices"
                }
                isLoading = false
            }

            override fun onFailure(call: Call<List<Notice>>, t: Throwable) {
                error = "Network error: ${t.message}"
                isLoading = false
            }
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                actions = {
                    IconButton(
                        onClick = {
                            RetrofitClient.instance.logout().enqueue(object : Callback<LogoutResponse> {
                                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                                    if (response.isSuccessful) {
                                        // Optionally log the success message
                                        Log.d("Logout", response.body()?.message ?: "Logged out successfully")
                                        context.startActivity(Intent(context, SignInActivity::class.java))
                                        (context as? ComponentActivity)?.finish()
                                    } else {
                                        // Handle unsuccessful response
                                        Log.e("Logout", "Logout failed: ${response.errorBody()?.string()}")
                                        context.startActivity(Intent(context, SignInActivity::class.java))
                                        (context as? ComponentActivity)?.finish()
                                    }
                                }

                                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                                    Log.e("Logout", "Network error: ${t.message}")
                                    context.startActivity(Intent(context, SignInActivity::class.java))
                                    (context as? ComponentActivity)?.finish()
                                }
                            })
                        }
                    ) {
                        Icon(Icons.Filled.ExitToApp, contentDescription = "Logout")
                    }
                }
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
            // View Profile Button
            Button(
                onClick = {
                    context.startActivity(Intent(context, ProfileActivity::class.java))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("View Profile")
            }

            Button(
                onClick = {
                    val complaintIntent = Intent(context, ComplaintActivity::class.java).apply {
                        putExtra("ROLL_NO", rollNo)
                    }
                    context.startActivity(complaintIntent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Register Complaint",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Register Complaint")
            }

            // Notices Section
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Notices",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 400.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(notices) { notice ->
                            NoticeItem(notice)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoticeItem(notice: Notice) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = notice.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = notice.date,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = notice.time,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    HostelManagAppTheme {
        DashboardScreen(rollNo = 10)
    }
}