package com.luiz.valorantapi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(onNavigateToProfile: (String, String) -> Unit) {
    var gameName by remember { mutableStateOf("") }
    var tagLine by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0F1923), Color(0xFF1A0A0A))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "V",
                color = Color(0xFFFF4655),
                fontSize = 64.sp,
                fontWeight = FontWeight.Black
            )

            Text(
                text = "BUSCAR JOGADOR",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp
            )

            Text(
                text = "Digite seu Riot ID para continuar",
                color = Color.Gray,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 40.dp)
            )

            // Card de busca
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2634)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "RIOT ID",
                        color = Color(0xFFFF4655),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = gameName,
                        onValueChange = {
                            gameName = it
                            error = ""
                        },
                        label = { Text("Nome do jogador") },
                        placeholder = { Text("Ex: TenZ", color = Color.Gray) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF4655),
                            unfocusedBorderColor = Color(0xFF2A3A4A),
                            focusedLabelColor = Color(0xFFFF4655),
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color(0xFFFF4655)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = tagLine,
                        onValueChange = {
                            tagLine = it
                            error = ""
                        },
                        label = { Text("Tag") },
                        placeholder = { Text("Ex: NA1", color = Color.Gray) },
                        singleLine = true,
                        leadingIcon = {
                            Text(
                                text = "#",
                                color = Color(0xFFFF4655),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF4655),
                            unfocusedBorderColor = Color(0xFF2A3A4A),
                            focusedLabelColor = Color(0xFFFF4655),
                            unfocusedLabelColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color(0xFFFF4655)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (error.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = error,
                            color = Color(0xFFFF4655),
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            when {
                                gameName.isBlank() -> error = "Digite o nome do jogador"
                                tagLine.isBlank() -> error = "Digite a tag"
                                else -> onNavigateToProfile(gameName.trim(), tagLine.trim())
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF4655)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "BUSCAR",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            letterSpacing = 2.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Região: Americas (BR / LAS / LAN / NA)",
                color = Color.Gray,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}