package com.luiz.valorantapi.data.model

data class MatchListResponse(val puuid: String, val history: List<MatchHistoryItem>)

data class MatchHistoryItem(val matchId: String, val gameStartTimeMillis: Long, val teamId: String)
