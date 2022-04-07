package com.ewingelen.neighbor_chat.presentation.log_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.neighbor_chat.domain.use_cases.ChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val useCases: ChatUseCases
) : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _showError = mutableStateOf(false)
    val showError: State<Boolean> = _showError

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setShowError(showError: Boolean) {
        _showError.value = showError
    }

    fun logIn() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.logIn(userName.value)
        }
    }
}