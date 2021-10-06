package com.ashish.systemtask.ViewModal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ashish.systemtask.Modal.Employee
import com.ashish.systemtask.Repository.EmployeeRespository

class EmployeeViewModal(application: Application) : AndroidViewModel(application) {
    private val employeeRespository: EmployeeRespository
    val allEmployess: LiveData<List<Employee>>
    fun insert(list: List<Employee?>?) {
        employeeRespository.insert(list as List<Employee>?)
    }

    init {
        employeeRespository = EmployeeRespository(application)
        allEmployess = employeeRespository.allEmployee
    }
}