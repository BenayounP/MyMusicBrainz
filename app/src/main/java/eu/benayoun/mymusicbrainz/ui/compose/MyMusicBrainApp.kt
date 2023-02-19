package eu.benayoun.mymusicbrainz.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails.ArtistDetailsScreen

@Composable
fun MyMusicBrainApp() {
    val navController = rememberNavController()
    MyMusicBrainzNavHost(navController = navController)
}

@Composable
fun MyMusicBrainzNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onArtistItemClick = {
                    navController.navigate("artistDetail/$it")
                }
            )
        }
        composable(
            "artistDetail/{artistId}",
            arguments = listOf(navArgument("artistId") {
                type = NavType.StringType
            })
        ) {
            ArtistDetailsScreen()
        }
    }
}
