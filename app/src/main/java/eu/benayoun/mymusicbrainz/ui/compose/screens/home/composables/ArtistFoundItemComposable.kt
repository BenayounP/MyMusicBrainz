package eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions.padding8
import eu.benayoun.mymusicbrainz.data.model.Artist


@Composable
fun ArtistFoundItemComposable(modifier: Modifier = Modifier, artist: Artist) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = padding8),
            text = artist.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Justify
        )
        Text(
            modifier = Modifier
                .padding(horizontal = padding8),
            text = "country:${artist.country}-type:${artist.type}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Justify
        )
    }
}