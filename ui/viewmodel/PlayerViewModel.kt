package com.luiz.valorantapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luiz.valorantapi.data.model.HenrikAccountData
import com.luiz.valorantapi.data.model.HenrikMatch
import com.luiz.valorantapi.data.repository.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: ValorantRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Idle)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private val _matches = MutableStateFlow<List<HenrikMatch>>(emptyList())
    val matches: StateFlow<List<HenrikMatch>> = _matches.asStateFlow()

    fun resetState() {
        _uiState.value = PlayerUiState.Idle
        _matches.value = emptyList()
    }

    fun searchPlayer(gameName: String, tagLine: String) {
        viewModelScope.launch {
            _uiState.value = PlayerUiState.Loading

            repository.getAccount(gameName, tagLine)
                .onSuccess { response ->
                    val account = response.data
                    if (account == null) {
                        _uiState.value = PlayerUiState.Error("Jogador não encontrado")
                        return@onSuccess
                    }
                    _uiState.value = PlayerUiState.Success(account)
                    loadMatches(gameName, tagLine)
                }
                .onFailure { error ->
                    _uiState.value = PlayerUiState.Error(
                        "Jogador não encontrado"
                    )
                }
        }
    }

    private suspend fun loadMatches(gameName: String, tagLine: String) {
        repository.getMatches(gameName, tagLine)
            .onSuccess { response ->
                _matches.value = response.data?.take(10) ?: emptyList()
            }
            .onFailure {
                _matches.value = emptyList()
            }
    }
}

sealed class PlayerUiState {
    data object Idle : PlayerUiState()
    data object Loading : PlayerUiState()
    data class Success(val account: HenrikAccountData) : PlayerUiState()
    data class Error(val message: String) : PlayerUiState()
}