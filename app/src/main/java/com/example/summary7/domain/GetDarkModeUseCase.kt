package com.example.summary7.domain

import com.example.summary7.data.DarkModePreference
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(private val darkModePreference: DarkModePreference) {
    suspend operator fun invoke() =
        darkModePreference.getDarkMode()

}