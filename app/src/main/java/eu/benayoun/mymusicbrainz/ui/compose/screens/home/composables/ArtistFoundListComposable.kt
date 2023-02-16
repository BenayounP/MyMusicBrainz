package eu.benayoun.mymusicbrainz.ui.compose.screens.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.benayoun.mymusicbrainz.data.artistsearch.model.Artist
import eu.benayoun.mymusicbrainz.ui.theme.ComposeDimensions

@Composable
fun ArtistFoundListComposable(modifier: Modifier = Modifier, foundArtistsList: List<Artist>) {
    Column(
        modifier = modifier
    ) {
        val lazyState = rememberLazyListState()
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(ComposeDimensions.padding2)
        ) {
            items(foundArtistsList) { artist: Artist ->
                ArtistFoundItemComposable(artist = artist)
            }
        }
    }
}