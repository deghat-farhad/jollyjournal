package com.farhad.jollyjournal.com.farhad.jollyjournal.view.video

import android.net.Uri
import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.farhad.jollyjournal.com.farhad.jollyjournal.item.NewsItem
import com.farhad.jollyjournal.com.farhad.jollyjournal.utils.serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val ROUTE_VIDEO = "video_route"
const val ARG_VIDEO = "video_arg"
fun NavGraphBuilder.video(
    navController: NavController
) {
    composable(
        route = "$ROUTE_VIDEO/{$ARG_VIDEO}",
        arguments = listOf(
            navArgument(ARG_VIDEO) {
                type = VideoNavType()
            }
        )
    ) {
        VideoRoute(
            viewModel = hiltViewModel(),
            popBack = navController::popBackStack,
        )
    }
}

fun NavController.navigateToVideo(navOptions: NavOptions? = null, video: NewsItem.Video) {
    val json = Json {
        ignoreUnknownKeys = true
    }
    val videoJson = Uri.encode(json.encodeToString(video))
    this.navigate("$ROUTE_VIDEO/$videoJson", navOptions)
}

class VideoNavType : NavType<NewsItem.Video>(isNullableAllowed = false) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override fun get(bundle: Bundle, key: String): NewsItem.Video? {
        return bundle.serializable(key)
    }

    override fun parseValue(value: String): NewsItem.Video {
        return json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: NewsItem.Video) {
        bundle.putSerializable(key, value)
    }
}