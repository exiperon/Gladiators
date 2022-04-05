package com.ewingelen.neighbor_chat.presentation.log_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.neighbor_chat.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val dataStore: DataStore<AppSettings>
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
            dataStore.updateData {
                it.copy(
                    userIsAuthorized = true,
                    userName = _userName.value
                )
            }
        }
    }
}