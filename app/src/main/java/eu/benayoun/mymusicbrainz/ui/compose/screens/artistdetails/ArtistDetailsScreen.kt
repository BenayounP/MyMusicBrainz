package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions
import eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails.composables.ReleaseListComposable

@Composable
fun ArtistDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: ArtistDetailsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
    ) {
        val artist = viewModel.artistState.collectAsState().value

        // ARTIST
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = artist.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "type: ${artist.type}", //todo replace with xml string
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(ComposeDimensions.padding8))
        // RELEASES
        val releases = viewModel.releaseState.collectAsState().value
        if (releases.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "RELEASES",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(ComposeDimensions.padding8))
            ReleaseListComposable(releasesList = releases)
        }
    }
}