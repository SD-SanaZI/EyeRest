package com.sanazi.eyerest

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sanazi.eyerest.screens.article.Article
import com.sanazi.eyerest.screens.exerciseList.ExerciseList
import com.sanazi.eyerest.screens.exercises.exercise1.Exercise1
import com.sanazi.eyerest.screens.exercises.exercise2.Exercise2
import com.sanazi.eyerest.screens.exercises.exercise3.Exercise3
import com.sanazi.eyerest.screens.exercises.exercise4.Exercise4
import com.sanazi.eyerest.screens.exercises.exercise5.Exercise5
import com.sanazi.eyerest.screens.exercises.exercise6.Exercise6
import com.sanazi.eyerest.screens.exercises.exercise7.Exercise7
import com.sanazi.eyerest.screens.exercises.exercise8.Exercise8
import com.sanazi.eyerest.screens.main.Main
import com.sanazi.eyerest.screens.main.carousel.newsList
import com.sanazi.eyerest.screens.notification.Notification
import com.sanazi.eyerest.screens.settings.Settings
import com.sanazi.eyerest.screens.timeout.Timeout
import com.sanazi.eyerest.ui.theme.EyeFitTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        setContent {
            EyeFitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Image(
                        painterResource(R.drawable.background), null,
                        Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    MenuNavHost(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        navController
                    )
                }
            }
        }
    }
}

@Serializable
sealed class NavRoutes

@Serializable
data object Main : NavRoutes()

@Serializable
data class Article(val newsId: Int) : NavRoutes()

@Serializable
data object Settings : NavRoutes()

@Serializable
data object Notification : NavRoutes()

@Serializable
data object ExerciseList : NavRoutes()


@Serializable
data class Timeout(val route: NavRoutes) : NavRoutes()

@Serializable
data class Exercise1(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise2(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise3(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise4(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise5(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise6(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise7(val isAutoNext: Boolean) : NavRoutes()

@Serializable
data class Exercise8(val isAutoNext: Boolean) : NavRoutes()


val NavRoutesType = object : NavType<NavRoutes>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): NavRoutes? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun put(bundle: Bundle, key: String, value: NavRoutes) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun parseValue(value: String): NavRoutes = Json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: NavRoutes): String = Uri.encode(Json.encodeToString(value))
}

@Composable
fun MenuNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        startDestination = Main
    ) {
        composable<Main> { Main(navController, Modifier.fillMaxSize()) }
        composable<Article> { navBackStackEntry ->
            val article = navBackStackEntry.toRoute<Article>()
            newsList.getOrNull(article.newsId)
                ?.let { Article(it, navController, Modifier.fillMaxSize()) }
                ?: let { navController.popBackStack() }
        }
        composable<Settings> { Settings(navController, Modifier.fillMaxSize()) }
        composable<Notification> { Notification(navController, Modifier.fillMaxSize()) }
        composable<ExerciseList> { ExerciseList(navController, Modifier.fillMaxSize()) }
        composable<Timeout>(
            typeMap = mapOf(
                typeOf<NavRoutes>() to NavRoutesType
            )
        ) {
            Timeout(
                it.toRoute<Timeout>().route,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise1> {
            Exercise1(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise2> {
            Exercise2(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise3> {
            Exercise3(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise4> {
            Exercise4(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise5> {
            Exercise5(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise6> {
            Exercise6(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise7> {
            Exercise7(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
        composable<Exercise8> {
            Exercise8(
                it.toRoute<Exercise1>().isAutoNext,
                navController,
                Modifier.fillMaxSize()
            )
        }
    }
}