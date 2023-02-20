package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.repository.Repository
import eu.benayoun.mymusicbrainz.data.repository.di.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @RepositoryProvider private val repository: Repository
) :
    ViewModel() {
    private val artistId: String = savedStateHandle.get<String>("artistId")!!

    //artist data (without releases)
    private val _artistState = MutableStateFlow<Artist>(Artist.EmptyArtist())
    val artistState = _artistState.asStateFlow()


    init {
        getDataAndFlow()
    }

    // INTERNAL COOKING

    private fun getDataAndFlow() {
        viewModelScope.launch {
            val artist = repository.getArtist(artistId)
            _artistState.value = artist
            // I could have done that directly in the repository, but it would have violated a SOLID principle.
            repository.updateArtistReleases(artistId)
        }
    }
}