import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Router(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") { HomeScreen(navController) }
        composable("DetailedScreen",) { DetailedScreen(navController) }

    }
}
