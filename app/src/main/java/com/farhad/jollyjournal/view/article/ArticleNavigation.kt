package com.farhad.jollyjournal.com.farhad.jollyjournal.view.article

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

const val ROUTE_ARTICLE = "article_route"
const val ARG_ARTICLE = "article_arg"
fun NavGraphBuilder.article(
    navController: NavController
) {
    composable(
        route = "$ROUTE_ARTICLE/{$ARG_ARTICLE}",
        arguments = listOf(
            navArgument(ARG_ARTICLE) {
                type = ArticleNavType()
            }
        )
    ) {
        ArticleRoute(
            viewModel = hiltViewModel(),
        )
    }
}

fun NavController.navigateToArticle(navOptions: NavOptions? = null, article: NewsItem.Article) {
    val json = Json {
        ignoreUnknownKeys = true
    }
    val articleJson = Uri.encode(json.encodeToString(article))
    this.navigate("$ROUTE_ARTICLE/$articleJson", navOptions)
}

class ArticleNavType : NavType<NewsItem.Article>(isNullableAllowed = false) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    override fun get(bundle: Bundle, key: String): NewsItem.Article? {
        return bundle.serializable(key)
    }

    override fun parseValue(value: String): NewsItem.Article {
        return json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: NewsItem.Article) {
        bundle.putSerializable(key, value)
    }
}