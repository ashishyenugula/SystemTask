package com.ashish.systemtask.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ashish.systemtask.Dao.EmployeeDao
import com.ashish.systemtask.Modal.Employee

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao
    internal class PopulateAsynTask(employeeDatabase: EmployeeDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val employeeDao: EmployeeDao
        protected override fun doInBackground(vararg params: Void?): Void? {
            employeeDao.deleteAll()
            return null
        }

        init {
            employeeDao = employeeDatabase!!.employeeDao()
        }
    }

    companion object {
        private const val DATABASE_NAME = "EmployeeDatabase"

        @Volatile
        private var INSTANCE: EmployeeDatabase? = null
        fun getInstance(context: Context?): EmployeeDatabase? {
            if (INSTANCE == null) {
                synchronized(EmployeeDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context!!, EmployeeDatabase::class.java,
                            DATABASE_NAME
                        )
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        var callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsynTask(INSTANCE)
            }
        }
    }
}