package com.ewingelen.neighbor_chat.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewingelen.neighbor_chat.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<AppSettings>
) : ViewModel() {

    private val _userIsAuthorized = mutableStateOf(false)
    val userIsAuthorized: State<Boolean> = _userIsAuthorized

    init {
        userIsAuthorized()
    }

    private fun userIsAuthorized() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.data.collect {
                if (it.userIsAuthorized) _userIsAuthorized.value = true
            }
        }
    }
}