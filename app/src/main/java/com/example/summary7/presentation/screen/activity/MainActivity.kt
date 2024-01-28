package com.example.summary7.presentation.screen.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import com.example.summary7.R
import com.example.summary7.databinding.ActivityMainBinding
import com.example.summary7.databinding.NavHeaderBinding
import com.example.summary7.presentation.model.Item
import com.example.summary7.presentation.screen.fragment.RecyclerAdapter
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navView: NavigationView by lazy { binding.navigationView }
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        setUpNavigationView(navView)

    }

    private fun setUpDarkModeSwitch(headerBinding: NavHeaderBinding) {
        lifecycleScope.launch {
            val isDarkModeEnabled = viewModel.getDarkMode()
            headerBinding.swSwitch.isChecked = isDarkModeEnabled
            updateUiForDarkMode(isDarkModeEnabled)
        }

        headerBinding.swSwitch.setOnCheckedChangeListener { _, isChecked ->
            updateUiForDarkMode(isChecked)
        }
    }

    private fun setUpNavigationView(navigationView: NavigationView) {
        val headerView = navigationView.getHeaderView(0)
        val headerBinding = NavHeaderBinding.bind(headerView)
        setUpRecyclerView(headerBinding)
        setUpSwitchListeners(headerBinding)
    }

    private fun setUpRecyclerView(headerBinding: NavHeaderBinding) {
        val adapter = RecyclerAdapter()

        val menuItems = listOf(
            Item(0, R.drawable.ic_dashboard, getString(R.string.dashboard), null),
            Item(1, R.drawable.ic_message, getString(R.string.messages), 15),
            Item(0, R.drawable.ic_notification, getString(R.string.notifications), 20),
            Item(0, R.drawable.ic_calendar, getString(R.string.calendar), null),
            Item(0, R.drawable.ic_statistic, getString(R.string.statistics), null),
            Item(0, R.drawable.ic_settings, getString(R.string.settings), null),
        )

        headerBinding.recyclerView.adapter = adapter
        adapter.submitList(menuItems)
    }

    private fun setUpSwitchListeners(headerBinding: NavHeaderBinding) {
        headerBinding.swSwitchPersonalToBusiness.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                headerBinding.tvBusiness.visibility = View.GONE
                headerBinding.tvPersonal.visibility = View.VISIBLE
            } else {
                headerBinding.tvBusiness.visibility = View.VISIBLE
                headerBinding.tvPersonal.visibility = View.GONE
            }
        }

        setUpDarkModeSwitch(headerBinding)
    }

    private fun updateUiForDarkMode(isDarkModeEnabled: Boolean) {
        val mode =
            if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
        viewModel.saveDarkMode(isDarkModeEnabled)
    }
}