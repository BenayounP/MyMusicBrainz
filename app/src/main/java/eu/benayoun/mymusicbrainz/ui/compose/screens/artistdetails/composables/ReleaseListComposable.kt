package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions
import eu.benayoun.mymusicbrainz.data.model.Release

@Composable
fun ReleaseListComposable(
    modifier: Modifier = Modifier,
    releasesList: List<Release>
) {
    Column(
        modifier = modifier
    ) {
        val lazyState = rememberLazyListState()
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(ComposeDimensions.padding2)
        ) {
            items(releasesList) { release: Release ->
                ReleaseItemComposable(release = release)
            }
        }
    }
}