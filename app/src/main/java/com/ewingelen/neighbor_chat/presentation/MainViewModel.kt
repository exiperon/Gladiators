package com.ewingelen.neighbor_chat.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.neighbor_chat.core.Resource
import com.ewingelen.neighbor_chat.domain.use_cases.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: ChatUseCases
) : ViewModel() {

    private val _userIsAuthorized = mutableStateOf(false)
    val userIsAuthorized: State<Boolean> = _userIsAuthorized

    private val _loadingFinished = mutableStateOf(false)
    val loadingFinished: State<Boolean> = _loadingFinished

    init {
        userIsAuthorized()
    }

    private fun userIsAuthorized() {
        useCases.getUser().onEach { result ->
            when(result) {
                is Resource.Loading -> _loadingFinished.value = false
                is Resource.Success -> {
                    _loadingFinished.value = true
                    _userIsAuthorized.value = result.data?.id != null
                }
                is Resource.Error -> {
                    _loadingFinished.value = true
                    _userIsAuthorized.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}