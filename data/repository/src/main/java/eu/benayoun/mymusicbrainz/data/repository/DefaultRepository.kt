package eu.benayoun.mymusicbrainz.data.repository


import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.repository.source.network.MusicBrainzAPISource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class DefaultRepository(
    private val musicBrainzDataSource: MusicBrainzAPISource,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : Repository {

    /**
     * SEARCH
     */

    private val searchMutex = Mutex()
    private val _searchArtistResponseFlow =
        MutableStateFlow<MusicBrainArtistSearchAPIResponse>(MusicBrainArtistSearchAPIResponse.Empty())

    override suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainArtistSearchAPIResponse> =
        _searchArtistResponseFlow

    override fun searchArtist(query: String) {
        externalScope.launch(dispatcher) {
            searchMutex.withLock() {
                _searchArtistResponseFlow.value = musicBrainzDataSource.searchArtist(query)
            }
        }
    }
}