package eu.benayoun.mymusicbrainz.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.ui.compose.screens.home.HomeViewModel
import eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables.ArtistFoundListComposable
import eu.benayoun.mymusicbrainz.ui.theme.ComposeDimensions.padding2

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(padding2),

        ) {
        val response = viewModel.musicBrainzArtistSearchAPIResponseState.collectAsState().value
        if (response is MusicBrainzArtistSearchAPIResponse.Success) {
            ArtistFoundListComposable(foundArtistsList = response.artistsList)
        }
    }
}