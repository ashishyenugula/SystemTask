package com.ashish.systemtask.Network

import com.ashish.systemtask.Modal.DataResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @get:GET("GetEmployeeData")
    val allEmployees: Call<DataResponse>
}