package com.ashish.systemtask.Repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData

import com.ashish.systemtask.Dao.EmployeeDao
import com.ashish.systemtask.Database.EmployeeDatabase
import com.ashish.systemtask.Modal.Employee

@Suppress("DEPRECATION")
class EmployeeRespository(application: Context) {
    private val database: EmployeeDatabase
    private val getAllEmployee: LiveData<List<Employee>>


    fun insert(employeeList: List<Employee>?) {
        InsertAsynTask(database).execute(employeeList)
    }


    val allEmployee: LiveData<List<Employee>>
        get() = getAllEmployee

    internal class InsertAsynTask(employeeDatabase: EmployeeDatabase) :
        AsyncTask<List<Employee?>?, Void?, Void?>() {
        private val employeeDao: EmployeeDao


        init {
            employeeDao = employeeDatabase.employeeDao()
        }

        override fun doInBackground(vararg lists: List<Employee?>?): Void? {
            employeeDao.insert(lists[0])
            return null
        }
    }

    init {
        database = EmployeeDatabase.getInstance(application)!!
        getAllEmployee = database.employeeDao().allEmployee()
    }
}