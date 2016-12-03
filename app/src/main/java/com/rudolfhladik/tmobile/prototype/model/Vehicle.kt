package com.rudolfhladik.tmobile.prototype.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Created by rd on 03/12/2016.
 */
class Vehicle(
        @SerializedName("Name")
        @Expose
        var vehicleName: String = "",
        @SerializedName("VIN")
        @Expose
        var vin: String = "",
        @SerializedName("Image")
        @Expose
        var carImage: JsonObject, //Src
        @SerializedName("CreatedOn")
        @Expose
        var year: String = "",
        @SerializedName("Odometer")
        @Expose
        var mileage: JsonObject //Value
) {

}
