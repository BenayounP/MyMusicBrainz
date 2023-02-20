package eu.benayoun.mymusicbrainz.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding4
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding8
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.ui.compose.screens.home.HomeViewModel
import eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables.ArtistListComposable
import eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables.SearchArtistTextField


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onArtistItemClick: (artistId: String) -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        //textField
        SearchArtistTextField(onButtonClick = viewModel::searchArtist)

        //Saved Artist
        val last3SavedArtists = viewModel.last3SavedArtistsState.collectAsState().value
        if (last3SavedArtists.isNotEmpty()) {
            Spacer(modifier = Modifier.height(padding8))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = padding4),
                text = "Last Artists", // todo use an xml resource
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(padding8))
            ArtistListComposable(
                artists = last3SavedArtists,
                onArtistItemClick = onArtistItemClick
            )
        }

        val ongoingResearch = viewModel.ongoingResearch.collectAsState().value

        if (ongoingResearch) {
            Column(
                modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Processing your research", // todo use an xml resource
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
        // search result
        else {
            val response = viewModel.musicBrainzArtistSearchAPIResponseState.collectAsState().value
            // success
            if (response is MusicBrainzArtistSearchAPIResponse.Success) {
                Spacer(modifier = Modifier.height(padding8))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = padding4),
                    text = "Search Result", // todo use an xml resource
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(padding8))
                ArtistListComposable(
                    artists = response.artists,
                    onArtistItemClick = onArtistItemClick
                )
            } else if (response is MusicBrainzArtistSearchAPIResponse.Error)// error
            {
                Column(
                    modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = response.musicBrainzAPIError.toString(), // todo use an xml resource
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}