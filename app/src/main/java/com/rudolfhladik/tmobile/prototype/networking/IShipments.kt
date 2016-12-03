package com.rudolfhladik.tmobile.prototype.networking

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

/**
 *
 * Created by rd on 14/10/16.
 */
interface IShipments {


    @GET("shipments?projection=expand")
    fun getAllShipments(): Observable<Response<JsonObject>>

    @Headers("Accept: application/json")
    @POST("shipmentUpdates")
    fun updateShipment(@Body update: JsonObject): Observable<Response<JsonObject>>
}