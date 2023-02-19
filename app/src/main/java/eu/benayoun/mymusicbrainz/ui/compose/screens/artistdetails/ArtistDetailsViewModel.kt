package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.repository.Repository
import eu.benayoun.mymusicbrainz.data.repository.di.RepositoryProvider
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(@RepositoryProvider private val repository: Repository) :
    ViewModel() {
}