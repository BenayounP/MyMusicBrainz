package eu.benayoun.mymusicbrainz.data.repository


import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse
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

    override suspend fun getSearchedArtist(artistId: String): Artist {
        val searchedArtistResponse = _searchArtistResponseFlow.value
        return if (searchedArtistResponse is MusicBrainzArtistSearchAPIResponse.Success) {
            searchedArtistResponse.artists.first { artist: Artist -> artist.id == artistId }
        } else Artist.EmptyArtist()
    }

    /**
     * COMPLETE ARTIST DATA WITH RELEASES
     */

    private val releasesMutex = Mutex()
    private val _getArtistReleasesResponseFlow =
        MutableStateFlow<MusicBrainzGetArtistReleasesAPIResponse>(
            MusicBrainzGetArtistReleasesAPIResponse.Empty()
        )

    override suspend fun getArtistReleasesResponseFlow(artistId: String): Flow<MusicBrainzGetArtistReleasesAPIResponse> {
        externalScope.launch(dispatcher) {
            releasesMutex.withLock() {
                _getArtistReleasesResponseFlow.value = musicBrainzDataSource.getReleases(artistId)
            }
        }
        return _getArtistReleasesResponseFlow
    }

}