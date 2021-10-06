package com.ashish.systemtask.activity

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ashish.systemtask.Database.EmployeeDatabase
import com.ashish.systemtask.Modal.Employee
import com.ashish.systemtask.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


@Suppress("DEPRECATION")
class UpdateActivity : AppCompatActivity() {

    private lateinit var editTextId: EditText
    private lateinit var editTextDesc: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextSalary: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var image: CircleImageView
    private lateinit var update: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        editTextId = findViewById(R.id.editTextId);
        editTextDesc = findViewById(R.id.editTextdesc);
        editTextName = findViewById(R.id.editTextName);
        editTextSalary = findViewById(R.id.editTextSalary)
        editTextEmail = findViewById(R.id.editTextEmail)
        image = findViewById(R.id.image)
        update = findViewById(R.id.button_update)
        val item: Employee? = intent.getParcelableExtra<Employee>("item")


        loadDetails(item)
        update.setOnClickListener {
            updateEmployee(item)
        }

    }

    private fun updateEmployee(item: Employee?) {
        val id: String = editTextId.getText().toString().trim()
        val designation: String = editTextDesc.getText().toString().trim()
        val name: String = editTextName.getText().toString().trim()
        val salary: String = editTextSalary.getText().toString().trim()
        val email: String = editTextEmail.getText().toString().trim()
        if (id.isEmpty()) {
            editTextId.setError("EmpId required");
            editTextId.requestFocus();
            return;
        }

        if (designation.isEmpty()) {
            editTextDesc.setError("Designation required");
            editTextDesc.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }
        if (salary.isEmpty()) {
            editTextSalary.setError("Salary required");
            editTextSalary.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email required");
            editTextEmail.requestFocus();
            return;
        }

        class SaveTask :
            AsyncTask<Void?, Void?, Void?>() {

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                finish()

                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
            }

            override fun doInBackground(vararg params: Void?): Void? {

                item?.empId = id.toInt()
                item?.empName = name
                item?.email = email
                item?.salary = salary.toInt()
                item?.designation = designation
                //adding to database
                EmployeeDatabase.getInstance(this@UpdateActivity)

                    ?.employeeDao()
                    ?.updateEmployee(item!!)
                return null
            }
        }

        val st = SaveTask()
        st.execute()
    }

    private fun loadDetails(item: Employee?) {

        editTextId.setText(item?.empId.toString())
        editTextDesc.setText(item?.designation!!)
        editTextName.setText(item.empName)
        editTextEmail.setText(item.email)
        editTextSalary.setText(item.salary.toString())
        Glide.with(this)
            .load(item.image)
            .into(image)
    }

}

