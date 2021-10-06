package com.ashish.systemtask.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.systemtask.Adapter.EmployeeAdapter
import com.ashish.systemtask.Modal.DataResponse
import com.ashish.systemtask.Modal.Employee
import com.ashish.systemtask.Network.Retrofit
import com.ashish.systemtask.R
import com.ashish.systemtask.Repository.EmployeeRespository
import com.ashish.systemtask.ViewModal.EmployeeViewModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var employeeViewModal: EmployeeViewModal
    private lateinit var retrofit: Retrofit
    private lateinit var recyclerView: RecyclerView
    private lateinit var employeeList: List<Employee>
    private lateinit var employeeRespository: EmployeeRespository
    private lateinit var employeeAdapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setItemAnimator(DefaultItemAnimator())
        employeeRespository = EmployeeRespository(application)
        employeeList = ArrayList<Employee>()
        employeeAdapter = EmployeeAdapter(this, employeeList)

        employeeViewModal = ViewModelProvider(this).get(EmployeeViewModal::class.java)
        networkRequest()
        employeeViewModal.allEmployess.observe(this,
            Observer<List<Any?>> { list ->
                recyclerView.setAdapter(employeeAdapter)
                employeeAdapter.getAllEmployess(list as List<Employee>)
                Log.d("main", "onChanged: $list")
            })
    }

    private fun networkRequest() {
        val retrofit = Retrofit()
        val call: Call<DataResponse> = retrofit.api.allEmployees
        call.enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {
                    employeeRespository.insert(response.body()?.employees);
                    Log.d("main", "onResponse: $response")
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "something went wrong...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}