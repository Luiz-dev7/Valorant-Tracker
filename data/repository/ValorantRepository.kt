package com.luiz.valorantapi.data.repository

import com.luiz.valorantapi.data.api.RiotApi
import com.luiz.valorantapi.data.model.HenrikAccountResponse
import com.luiz.valorantapi.data.model.HenrikMatchesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValorantRepository @Inject constructor(private val api: RiotApi) {
    suspend fun getAccount(
        gameName: String,
        tagLine: String
    ): Result<HenrikAccountResponse> =
        runCatching { api.getAccount(gameName, tagLine) }

    suspend fun getMatches(
        gameName: String,
        tagLine: String,
        region: String = "br"
    ): Result<HenrikMatchesResponse> =
        runCatching { api.getMatches(region, gameName, tagLine) }
}