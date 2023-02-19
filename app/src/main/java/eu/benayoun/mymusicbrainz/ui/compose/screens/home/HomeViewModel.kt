package eu.benayoun.mymusicbrainz.ui.compose.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
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
    private val _musicBrainzArtistSearchAPIResponseState =
        MutableStateFlow<MusicBrainzArtistSearchAPIResponse>(MusicBrainzArtistSearchAPIResponse.Empty())
    val musicBrainzArtistSearchAPIResponseState: StateFlow<MusicBrainzArtistSearchAPIResponse> =
        _musicBrainzArtistSearchAPIResponseState.asStateFlow()

    init {
        getFlow()
    }

    fun searchArtist(query: String) {
        _ongoingResearch.value = true
        _musicBrainzArtistSearchAPIResponseState.value = MusicBrainzArtistSearchAPIResponse.Empty()
        repository.searchArtist(query)
    }

    // INTERNAL COOKING

    private fun getFlow() {
        // questions
        viewModelScope.launch {
            repository.getSearchArtistResponseFlow().flowOn(Dispatchers.IO)
                .collect { musicBrainzArtistSearchAPIResponse: MusicBrainzArtistSearchAPIResponse ->
                    _ongoingResearch.value = false
                    _musicBrainzArtistSearchAPIResponseState.value =
                        if (musicBrainzArtistSearchAPIResponse is MusicBrainzArtistSearchAPIResponse.Success) {
                            MusicBrainzArtistSearchAPIResponse.Success(
                                sortSearchResult(
                                    musicBrainzArtistSearchAPIResponse.artists
                                )
                            )
                        } else musicBrainzArtistSearchAPIResponse
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