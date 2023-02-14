package eu.benayoun.mymusicbrainz.ui.compose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.di.RepositoryProvider
import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(@RepositoryProvider private val repository: Repository) :
    ViewModel() {
    // search artists
    private val _musicBrainzArtistSearchAPIResponseState =
        MutableStateFlow<MusicBrainzArtistSearchAPIResponse>(MusicBrainzArtistSearchAPIResponse.Empty())
    val musicBrainzArtistSearchAPIResponseState: StateFlow<MusicBrainzArtistSearchAPIResponse> =
        _musicBrainzArtistSearchAPIResponseState.asStateFlow()

    init {
        getFlow()
        repository.searchArtist("divine")
    }


    // INTERNAL COOKING

    private fun getFlow() {
        // questions
        viewModelScope.launch {
            repository.getSearchArtistResponseFlow().flowOn(Dispatchers.IO)
                .collect { musicBrainzArtistSearchAPIResponse: MusicBrainzArtistSearchAPIResponse ->
                    _musicBrainzArtistSearchAPIResponseState.value =
                        musicBrainzArtistSearchAPIResponse
                }
        }
    }
}