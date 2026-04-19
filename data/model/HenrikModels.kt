package com.luiz.valorantapi.data.model

data class HenrikAccountResponse(
    val status: Int,
    val data: HenrikAccountData?
)

data class HenrikAccountData(
    val puuid: String?,
    val name: String?,
    val tag: String?,
    val card: HenrikCard?,
    val account_level: Int?
)

data class HenrikCard(
    val small: String?,
    val large: String?,
    val wide: String?
)

data class HenrikMatchesResponse(
    val status: Int,
    val data: List<HenrikMatch>?
)

data class HenrikMatch(
    val metadata: HenrikMatchMetadata,
    val players: HenrikMatchPlayers,
    val teams: HenrikTeams?
)

data class HenrikMatchMetadata(
    val matchid: String?,
    val map: String?,
    val game_length: Int?,
    val game_start_patched: String?,
    val mode: String?
)

data class HenrikMatchPlayers(
    val all_players: List<HenrikPlayer>
)

data class HenrikPlayer(
    val puuid: String?,
    val name: String?,
    val tag: String?,
    val character: String?,
    val currenttier_patched: String?,
    val stats: HenrikStats?,
    val team: String?
)

data class HenrikStats(
    val kills: Int?,
    val deaths: Int?,
    val assists: Int?,
    val bodyshots: Int?,
    val headshots: Int?,
    val legshots: Int?
)

data class HenrikTeams(
    val red: HenrikTeamResult?,
    val blue: HenrikTeamResult?
)

data class HenrikTeamResult(
    val has_won: Boolean,
    val rounds_won: Int,
    val rounds_lost: Int
)