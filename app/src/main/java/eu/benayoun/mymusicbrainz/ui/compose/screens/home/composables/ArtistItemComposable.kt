package eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun ArtistItemComposable(
    modifier: Modifier = Modifier,
    artist: Artist,
    onClick: (artistId: String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick(artist.id) },
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
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Justify
        )
    }
}