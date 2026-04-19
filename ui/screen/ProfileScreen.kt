package com.luiz.valorantapi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.luiz.valorantapi.data.model.HenrikMatch
import com.luiz.valorantapi.ui.viewmodel.PlayerUiState
import com.luiz.valorantapi.ui.viewmodel.PlayerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    gameName: String,
    tagLine: String,
    onBack: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(initialValue = PlayerUiState.Idle)
    val matches by viewModel.matches.collectAsStateWithLifecycle(initialValue = emptyList())

    LaunchedEffect(gameName, tagLine) {
        viewModel.resetState()
        viewModel.searchPlayer(gameName, tagLine)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "$gameName#$tagLine",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color(0xFFFF4655)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0F1923)
                )
            )
        },
        containerColor = Color(0xFF0F1923)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF0F1923), Color(0xFF1A0A0A))
                    )
                )
        ) {
            when (val state = uiState) {

                is PlayerUiState.Idle -> {}

                is PlayerUiState.Loading -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = Color(0xFFFF4655))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Buscando perfil...", color = Color.Gray)
                    }
                }

                is PlayerUiState.Error -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF1A2634)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚠️", fontSize = 40.sp)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Jogador não encontrado",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Não conseguimos encontrar nenhum jogador com esse Riot ID. Verifique o nome e a tag e tente novamente.",
                            color = Color.Gray,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1A2634)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "💡 DICAS",
                                    color = Color(0xFFFF4655),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "• O nome é case-sensitive: \"TenZ\" ≠ \"tenz\"",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "• A tag não tem o símbolo #",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "• Exemplo: Nome = \"TenZ\" / Tag = \"NA1\"",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF4655)
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "TENTAR NOVAMENTE",
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            )
                        }
                    }
                }

                is PlayerUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Card perfil
                        item {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF1A2634)
                                ),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(20.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Avatar do card do jogador
                                    if (state.account.card?.small != null) {
                                        AsyncImage(
                                            model = state.account.card.small,
                                            contentDescription = "Avatar",
                                            modifier = Modifier
                                                .size(72.dp)
                                                .clip(CircleShape)
                                        )
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .size(72.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    Brush.radialGradient(
                                                        colors = listOf(
                                                            Color(0xFFFF4655),
                                                            Color(0xFF8B0000)
                                                        )
                                                    )
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            val initial = state.account.name?.firstOrNull()?.toString()?.uppercase() ?: "?"
                                            Text(
                                                text = initial,
                                                color = Color.White,
                                                fontSize = 30.sp,
                                                fontWeight = FontWeight.Black
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column {
                                        Text(
                                            text = state.account.name ?: "Unknown",
                                            color = Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = "#${state.account.tag ?: ""}",
                                            color = Color(0xFFFF4655),
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Nível ${state.account.account_level ?: 0}",
                                            color = Color.Gray,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }

                        // Card stats
                        item {
                            val playerMatches = matches.filter { match ->
                                match.players.all_players.any {
                                    it.name.equals(state.account.name, ignoreCase = true) &&
                                            it.tag.equals(state.account.tag, ignoreCase = true)
                                }
                            }

                            val wins = playerMatches.count { match ->
                                val player = match.players.all_players.find {
                                    it.name.equals(state.account.name, ignoreCase = true)
                                }
                                val teamColor = player?.team?.lowercase()
                                if (teamColor == "red") match.teams?.red?.has_won == true
                                else match.teams?.blue?.has_won == true
                            }

                            val losses = playerMatches.size - wins
                            val winRate = if (playerMatches.isNotEmpty())
                                (wins * 100 / playerMatches.size) else 0

                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF1A2634)
                                ),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(20.dp)) {
                                    Text(
                                        text = "ÚLTIMAS ${playerMatches.size} PARTIDAS",
                                        color = Color(0xFFFF4655),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 2.sp
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        StatItem("VITÓRIAS", "$wins", Color(0xFF4CAF50))
                                        StatItem("DERROTAS", "$losses", Color(0xFFFF4655))
                                        StatItem("WIN RATE", "$winRate%", Color(0xFFFFD700))
                                    }
                                }
                            }
                        }

                        // Header histórico
                        item {
                            Text(
                                text = "HISTÓRICO DE PARTIDAS",
                                color = Color(0xFFFF4655),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        if (matches.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = Color(0xFFFF4655))
                                }
                            }
                        } else {
                            items(matches) { match ->
                                HenrikMatchCard(
                                    match = match,
                                    playerName = state.account.name ?: "",
                                    playerTag = state.account.tag ?: ""
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = color,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun HenrikMatchCard(
    match: HenrikMatch,
    playerName: String,
    playerTag: String
) {
    val player = match.players.all_players.find {
        it.name?.equals(playerName, ignoreCase = true) == true &&
                it.tag?.equals(playerTag, ignoreCase = true) == true
    }

    val teamColor = player?.team?.lowercase()
    val won = if (teamColor == "red") match.teams?.red?.has_won == true
    else match.teams?.blue?.has_won == true

    val kda = if (player != null && player.stats != null)
        "${player.stats.kills ?: 0} / ${player.stats.deaths ?: 0} / ${player.stats.assists ?: 0}"
    else "-/-/-"

    val hsPercent = if (player != null && player.stats != null) {
        val headshots = player.stats.headshots ?: 0
        val bodyshots = player.stats.bodyshots ?: 0
        val legshots = player.stats.legshots ?: 0
        val total = headshots + bodyshots + legshots
        if (total > 0) "${(headshots * 100 / total)}%" else "0%"
    } else "0%"

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (won) Color(0xFF0D2016) else Color(0xFF200D0D)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Barra lateral colorida
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(if (won) Color(0xFF4CAF50) else Color(0xFFFF4655))
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Modo e mapa
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (won) "VITÓRIA" else "DERROTA",
                    color = if (won) Color(0xFF4CAF50) else Color(0xFFFF4655),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Text(
                    text = match.metadata.mode ?: "Unknown Mode",
                    color = Color.White,
                    fontSize = 12.sp
                )
                Text(
                    text = match.metadata.map ?: "Unknown Map",
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }

            // KDA
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = kda,
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "K / D / A", color = Color.Gray, fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Headshot %
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = hsPercent,
                    color = Color(0xFFFFD700),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "HS%", color = Color.Gray, fontSize = 10.sp)
            }
        }
    }
}