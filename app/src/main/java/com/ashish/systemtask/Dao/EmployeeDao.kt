package com.ashish.systemtask.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ashish.systemtask.Modal.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employeeList: List<Employee?>?)

    @Query("SELECT * FROM employee")
    fun allEmployee(): LiveData<List<Employee>>

    @Query("DELETE FROM employee")
    fun deleteAll()

    @Update
    fun updateEmployee(vararg employee: Employee)

    @Delete
    fun delete(vararg employee: Employee)
}