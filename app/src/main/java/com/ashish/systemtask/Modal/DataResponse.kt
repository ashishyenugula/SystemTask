package com.ashish.systemtask.Modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataResponse {
    @SerializedName("MsgStatus")
    @Expose
    var MsgStatus: String? = null

    @SerializedName("Data")
    @Expose
    var employees: List<Employee>? = null



    override fun toString(): String {
        return "DataResponse{" +
                "MsgStatus='" + MsgStatus + '\'' +
                ", employees=" + employees +
                '}'
    }
}