package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import eu.benayoun.mymusicbrainz.core.designsystem.theme.ComposeDimensions
import eu.benayoun.mymusicbrainz.data.model.Release

@Composable
fun ReleaseItemComposable(
    modifier: Modifier = Modifier,
    release: Release
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = ComposeDimensions.padding8),
            text = release.title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Justify
        )
        Text(
            modifier = Modifier
                .padding(horizontal = ComposeDimensions.padding8),
            text = release.date,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Justify
        )
    }
}