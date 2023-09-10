package com.farhad.jollyjournal.com.farhad.jollyjournal.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.article.article
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.ROUTE_HOME
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.home.home
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.video.video
import com.farhad.jollyjournal.ui.theme.JollyJournalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            JollyJournalTheme {
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_HOME,
                ) {
                    home(navController)
                    video(navController)
                    article(navController)
                }
            }
        }
    }
}