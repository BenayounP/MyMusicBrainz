package eu.benayoun.mymusicbrainz.ui.compose.screens.artistdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse
import eu.benayoun.mymusicbrainz.data.repository.Repository
import eu.benayoun.mymusicbrainz.data.repository.di.RepositoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
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

    //releases
    private val _musicBrainzGetArtistReleasesAPIResponseState =
        MutableStateFlow<MusicBrainzGetArtistReleasesAPIResponse>(
            MusicBrainzGetArtistReleasesAPIResponse.Empty()
        )
    val musicBrainzGetArtistReleasesAPIResponseState: StateFlow<MusicBrainzGetArtistReleasesAPIResponse> =
        _musicBrainzGetArtistReleasesAPIResponseState.asStateFlow()

    init {
        getDataAndFlow()
    }

    // INTERNAL COOKING

    private fun getDataAndFlow() {
        viewModelScope.launch {
            _artistState.value = repository.getSearchedArtist(artistId)
            repository.getArtistReleasesResponseFlow(artistId).flowOn(Dispatchers.IO)
                .collect { musicBrainzGetArtistReleasesAPIResponse: MusicBrainzGetArtistReleasesAPIResponse ->
                    _musicBrainzGetArtistReleasesAPIResponseState.value =
                        musicBrainzGetArtistReleasesAPIResponse
                }
        }
    }
}