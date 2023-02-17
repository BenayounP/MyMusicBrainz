package eu.benayoun.mymusicbrainz.ui.compose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainArtistSearchAPIResponse
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
class HomeViewModel @Inject constructor(@RepositoryProvider private val repository: Repository) :
    ViewModel() {

    // ongoing research
    private val _ongoingResearch = MutableStateFlow<Boolean>(false)
    val ongoingResearch = _ongoingResearch.asStateFlow()


    // search artists
    private val _musicBrainArtistSearchAPIResponseState =
        MutableStateFlow<MusicBrainArtistSearchAPIResponse>(MusicBrainArtistSearchAPIResponse.Empty())
    val musicBrainArtistSearchAPIResponseState: StateFlow<MusicBrainArtistSearchAPIResponse> =
        _musicBrainArtistSearchAPIResponseState.asStateFlow()

    init {
        getFlow()
    }

    fun searchArtist(query: String) {
        _ongoingResearch.value = true
        _musicBrainArtistSearchAPIResponseState.value = MusicBrainArtistSearchAPIResponse.Empty()
        repository.searchArtist(query)
    }


    // INTERNAL COOKING

    private fun getFlow() {
        // questions
        viewModelScope.launch {
            repository.getSearchArtistResponseFlow().flowOn(Dispatchers.IO)
                .collect { musicBrainArtistSearchAPIResponse: MusicBrainArtistSearchAPIResponse ->
                    _ongoingResearch.value = false
                    _musicBrainArtistSearchAPIResponseState.value =
                        if (musicBrainArtistSearchAPIResponse is MusicBrainArtistSearchAPIResponse.Success) {
                            MusicBrainArtistSearchAPIResponse.Success(
                                sortSearchResult(
                                    musicBrainArtistSearchAPIResponse.artists
                                )
                            )
                        } else musicBrainArtistSearchAPIResponse
                }
        }
    }

    // we display in the first place results with full data (i.e no "?")
    private fun sortSearchResult(unsortedArtistsList: List<Artist>): List<Artist> =
        unsortedArtistsList.sortedWith(compareBy(
            { artist: Artist ->
                artist.country == "?"
            },
            { artist: Artist -> artist.type == "?" || artist.type == "" },
            { artist: Artist -> artist.name })
        )


}