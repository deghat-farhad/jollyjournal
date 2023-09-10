package com.farhad.jollyjournal.com.farhad.jollyjournal.view.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.article.navigateToArticle
import com.farhad.jollyjournal.com.farhad.jollyjournal.view.video.navigateToVideo

const val ROUTE_HOME = "home_route"
fun NavGraphBuilder.home(
    navController: NavController
) {
    composable(ROUTE_HOME) {
        HomeRoute(
            viewModel = hiltViewModel(),
            navigateToArticle = {
                navController.navigateToArticle(article = it)
            },
            navigateToVideo = {
                navController.navigateToVideo(video = it)
            }
        )
    }
}