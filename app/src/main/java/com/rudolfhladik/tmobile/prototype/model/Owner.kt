package com.rudolfhladik.tmobile.prototype.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 *
 * Created by rd on 03/12/2016.
 */
class Owner(@SerializedName("UserName")
            @Expose
            var userName: String = "",
            @SerializedName("LastModified")
            @Expose
            var lastRide: String = "",
            @SerializedName("Image")
            @Expose
            var imgUrl: String = "",
            var type: String = "",
            var origin: String = "") {

}
