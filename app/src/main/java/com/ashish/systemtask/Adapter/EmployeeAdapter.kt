package com.ashish.systemtask.Adapter

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ashish.systemtask.Database.EmployeeDatabase
import com.ashish.systemtask.Modal.Employee
import com.ashish.systemtask.R
import com.ashish.systemtask.activity.UpdateActivity
import com.bumptech.glide.Glide


@Suppress("DEPRECATION")
class EmployeeAdapter(private val context: Context, private var employees: List<Employee>) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_employee, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = employees[position]
        holder.id.text = "Id :" + item.empId
        holder.name.text = "Name :" + item.empName
        holder.salary.text = "Salary: " + item.salary
        holder.desc.text = "Designation: " + item.designation
        holder.email.text = "Email: " + item.email
        holder.delete.setOnClickListener {
            deleteEmp(item)
        }
        holder.edit.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("item", item)

            context.startActivity(intent)
        }
        Glide.with(context)
            .load(item.image)
            .into(holder.image)
    }

    private fun deleteEmp(item: Employee) {


        class DeleteTask :
            AsyncTask<Void?, Void?, Void?>() {


            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                Toast.makeText(
                    context,
                    "Deleted",
                    Toast.LENGTH_LONG
                ).show()

            }

            override fun doInBackground(vararg params: Void?): Void? {
                EmployeeDatabase.getInstance(context)

                    ?.employeeDao()
                    ?.delete(item)
                return null
            }
        }

        val dt = DeleteTask()
        dt.execute()
    }

    fun getAllEmployess(employeeList: List<Employee>) {
        employees = employeeList
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var name: TextView
        var salary: TextView
        var image: ImageView
        var email: TextView
        var desc: TextView
        var delete: ImageView
        var edit: ImageView

        init {
            id = itemView.findViewById(R.id.id)
            name = itemView.findViewById(R.id.name)
            salary = itemView.findViewById(R.id.salary)
            image = itemView.findViewById(R.id.image)
            email = itemView.findViewById(R.id.email)
            desc = itemView.findViewById(R.id.designation)
            delete = itemView.findViewById(R.id.delete)
            edit = itemView.findViewById(R.id.edit)
        }
    }
}