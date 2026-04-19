package com.luiz.valorantapi.data.model

data class MatchResponse(
    val metadata: MatchMetadata,
    val info: MatchInfo
)

data class MatchMetadata(
    val matchId: String,
    val participants: List<String>
)


data class MatchInfo(
    val gameLengthMillis: Long,
    val gameStartMillis: Long,
    val players: List<MatchPlayer>
)

data class MatchPlayer(
    val puuid: String,
    val gameName: String,
    val tagLine: String,
    val characterId: String,
    val competitiveTier: Int,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val score: Int,
    val won: Boolean,
    val teamId: String
)