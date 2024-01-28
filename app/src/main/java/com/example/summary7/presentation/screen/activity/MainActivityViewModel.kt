package com.example.summary7.presentation.screen.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summary7.domain.GetDarkModeUseCase
import com.example.summary7.domain.SetDarkModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase
) : ViewModel() {

    suspend fun getDarkMode(): Boolean {
        return getDarkModeUseCase()
    }

    fun saveDarkMode(enabled: Boolean) = viewModelScope.launch {
        setDarkModeUseCase(enabled)
    }
}