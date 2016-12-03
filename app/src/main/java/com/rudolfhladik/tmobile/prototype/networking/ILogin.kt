package com.rudolfhladik.tmobile.prototype.networking

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 *
 * Created by rd on 03/12/2016.
 */
interface ILogin {
    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(@Field("grant_type") grantType: String = "password",
                 @Field("username") username: String = "richard.margeta@gmail.com",
                 @Field("password") password: String = "hovno753",
                 @Field("client_id") clientId: String = "0b655654-4021-43d3-b556-5da5f8ec4d90",
                 @Field("client_secret") clientSecret: String = "fbb2113a-9260-4c4a-8be3-2e0e7573a6f9",
                 @Field("redirect_uri") redirUrl: String = "app://asdf"): Observable<Response<JsonObject>>

    @POST("oauth2/token")
    @FormUrlEncoded
    fun refreshToken(@Field("grant_type") grantType: String = "refresh_token",
                     @Field("refresh_token") refreshToken: String,
                     @Field("redirect_uri") redirUrl: String = "app://asdf")
            : Observable<Response<JsonObject>>
}

