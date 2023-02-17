package eu.benayoun.mymusicbrainz.data.artistsearch.repository


import eu.benayoun.mymusicbrainz.data.artistsearch.model.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.artistsearch.source.network.MusicBrainzAPISource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class DefaultSearchRepository(
    private val musicBrainzDataSource: MusicBrainzAPISource,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : SearchRepository {

    /**
     * SEARCH
     */

    private val searchMutex = Mutex()
    private val _searchArtistResponseFlow =
        MutableStateFlow<MusicBrainzArtistSearchAPIResponse>(MusicBrainzArtistSearchAPIResponse.Empty())

    override suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse> =
        _searchArtistResponseFlow

    override fun searchArtist(query: String) {
        externalScope.launch(dispatcher) {
            searchMutex.withLock() {
                _searchArtistResponseFlow.value = musicBrainzDataSource.searchArtist(query)
            }
        }
    }
}