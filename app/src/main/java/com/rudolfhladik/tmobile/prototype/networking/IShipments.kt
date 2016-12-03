package com.rudolfhladik.tmobile.prototype.networking

import com.google.gson.JsonObject
import io.moj.java.sdk.model.response.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

/**
 *
 * Created by rd on 14/10/16.
 */
interface IShipments {
    @POST("oauth2/token")
    @FormUrlEncoded
    fun login(@Field("grant_type") grantType: String,
              @Field("username") username: String,
              @Field("password") password: String,
              @Field("client_id") clientId: String,
              @Field("client_secret") clientSecret: String): Call<AuthResponse>

    @GET("shipments?projection=expand")
    fun getAllShipments(): Observable<Response<JsonObject>>

    @Headers("Accept: application/json")
    @POST("shipmentUpdates")
    fun updateShipment(@Body update: JsonObject): Observable<Response<JsonObject>>
}