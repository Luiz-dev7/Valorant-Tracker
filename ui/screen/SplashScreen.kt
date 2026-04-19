package com.luiz.valorantapi.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToSearch: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "splash_alpha"
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(2500)
        onNavigateToSearch()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0F1923), Color(0xFF1A0A0A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.alpha(alpha)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(Color(0xFFFF4655), Color(0xFF8B0000))
                        ),
                        shape = androidx.compose.foundation.shape.CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "V",
                    color = Color.White,
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "VALORANT",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 8.sp
            )

            Text(
                text = "TRACKER",
                color = Color(0xFFFF4655),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 12.sp
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Estatísticas • Partidas • Perfil",
                color = Color.Gray,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}