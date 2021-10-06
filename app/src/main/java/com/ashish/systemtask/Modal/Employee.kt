package com.ashish.systemtask.Modal

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employee", indices = [Index(value = ["empId"], unique = true)])
class Employee(
    @field:ColumnInfo(name = "empId") @field:SerializedName("EmpId") var empId: Int,
    @field:ColumnInfo(
        name = "empName"
    ) @field:SerializedName(
        "EmpName"
    ) var empName: String,
    @field:ColumnInfo(name = "image") @field:SerializedName("PhotoUrl") var image: String?,
    @field:ColumnInfo(
        name = "salary"
    ) @field:SerializedName(
        "Salary"
    ) var salary: Int,
    @field:ColumnInfo(name = "email") @field:SerializedName("Email") var email: String?,
    @field:ColumnInfo(
        name = "designation"
    ) @field:SerializedName(
        "Designation"
    ) var designation: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun toString(): String {
        return "Employee{" +
                "id=" + id +
                ", empId=" + empId +
                ", empName='" + empName + '\'' +
                ", image='" + image + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                '}'
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(empId)
        parcel.writeString(empName)
        parcel.writeString(image)
        parcel.writeInt(salary)
        parcel.writeString(email)
        parcel.writeString(designation)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Employee> {
        override fun createFromParcel(parcel: Parcel): Employee {
            return Employee(parcel)
        }

        override fun newArray(size: Int): Array<Employee?> {
            return arrayOfNulls(size)
        }
    }
}