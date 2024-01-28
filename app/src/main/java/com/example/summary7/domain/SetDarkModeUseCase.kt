package com.example.summary7.domain

import com.example.summary7.data.DarkModePreference
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(private val darkModePreference: DarkModePreference) {
    suspend operator fun invoke(enabled: Boolean) {
        return darkModePreference.setDarkMode(enabled)
    }
}