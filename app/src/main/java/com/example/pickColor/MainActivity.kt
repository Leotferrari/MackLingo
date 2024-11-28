package com.example.pickColor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            WelcomeScreen(navController)
        }
        composable(
            "second_screen/{selectedFlag}/{yellowStarsCount}",
            arguments = listOf(
                navArgument("selectedFlag") { type = NavType.StringType },
                navArgument("yellowStarsCount") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val selectedFlag = backStackEntry.arguments?.getString("selectedFlag")
            val yellowStarsCount = backStackEntry.arguments?.getInt("yellowStarsCount") ?: 0
            QuizScreen(navController, selectedFlag, yellowStarsCount)
        }
        composable(
            "third_screen/{selectedFlag}/{selectedColor}/{starCount}",
            arguments = listOf(
                navArgument("selectedFlag") { type = NavType.StringType },
                navArgument("selectedColor") { type = NavType.StringType },
                navArgument("starCount") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val selectedFlag = backStackEntry.arguments?.getString("selectedFlag") ?: ""
            val selectedColor = backStackEntry.arguments?.getString("selectedColor") ?: ""
            val starCount = backStackEntry.arguments?.getInt("starCount") ?: 0
            CongratulationsScreen(navController, selectedFlag, selectedColor, starCount)
        }
        // Nova rota para AnimalsScreen
        composable(
            "fourth_screen/{selectedFlag}/{yellowStarsCount}",
            arguments = listOf(
                navArgument("selectedFlag") { type = NavType.StringType },
                navArgument("yellowStarsCount") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val selectedFlag = backStackEntry.arguments?.getString("selectedFlag")
            val yellowStarsCount = backStackEntry.arguments?.getInt("yellowStarsCount") ?: 0
            AnimalsScreen(navController, selectedFlag, yellowStarsCount)
        }
    }
}
