package com.rudolfhladik.tmobile.prototype.networking

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

/**
 *
 * Created by rd on 14/10/16.
 */
interface IShipments {
    @POST("ext-api/apps/v1")
    fun getAppList(@Header("Authorization") accessToken: String): Observable<Response<JsonObject>>

    @POST("ext-api/oauth_refresh_token/v1")
    fun refreshAccessToken(@Query("grant_type") type: String, @Query("refresh_token") code: String, @Query("scope") scope: String, @Query("device_info") info: String): Observable<Response<JsonObject>>

    @GET("api/v1/getActiveShipment")
    fun getActiveShipment(): Observable<Response<JsonArray>>

    @GET("shipments?projection=expand")
    fun getAllShipments(): Observable<Response<JsonObject>>

    @Headers("Accept: application/json")
    @POST("shipmentUpdates")
    fun updateShipment(@Body update: JsonObject): Observable<Response<JsonObject>>
}