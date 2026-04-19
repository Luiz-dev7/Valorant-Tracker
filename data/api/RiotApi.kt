package com.luiz.valorantapi.data.api

import com.luiz.valorantapi.data.model.HenrikAccountResponse
import com.luiz.valorantapi.data.model.HenrikMatchesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApi {

    @GET("valorant/v1/account/{name}/{tag}")
    suspend fun getAccount(
        @Path("name") name: String,
        @Path("tag") tag: String
    ): HenrikAccountResponse

    @GET("valorant/v3/matches/{region}/{name}/{tag}")
    suspend fun getMatches(
        @Path("region") region: String,
        @Path("name") name: String,
        @Path("tag") tag: String
    ): HenrikMatchesResponse
}