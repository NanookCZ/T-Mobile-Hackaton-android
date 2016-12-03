package com.rudolfhladik.tmobile.prototype.networking

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import rx.Observable

/**
 *
 * Created by rd on 03/12/2016.
 */
interface IRest {
    @GET("v2/vehicles")
    fun getVehicles(authorization: String): Observable<Response<JsonObject>>
}
