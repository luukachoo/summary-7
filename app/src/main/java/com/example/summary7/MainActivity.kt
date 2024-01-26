package com.example.summary7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summary7.databinding.ActivityMainBinding
import com.example.summary7.databinding.NavHeaderBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navigationView
        setUpNavigationView(navView)
    }


    private fun setUpNavigationView(navigationView: NavigationView) {
        val headerView = navigationView.getHeaderView(0)
        val headerBinding = NavHeaderBinding.bind(headerView)
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
}