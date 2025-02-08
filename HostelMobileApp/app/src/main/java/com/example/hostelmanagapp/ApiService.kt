package com.example.hostelmanagapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("students/signup")
    fun registerStudent(@Body student: Student): Call<Void>

    @POST("students/signin")
    fun loginStudent(@Body student: Student): Call<LoginResponse>

    @GET("students/viewNotices")
    fun getNotices(): Call<List<Notice>>

    @GET("students/profile")
    fun showProfile(): Call<Student>

    @POST("students/logout")
    fun logout(): Call<LogoutResponse>

    @POST("students/registerComplaint")
    fun registerComplaint(@Body complaintRequest: Complaint): Call<ComplaintResponse>
}