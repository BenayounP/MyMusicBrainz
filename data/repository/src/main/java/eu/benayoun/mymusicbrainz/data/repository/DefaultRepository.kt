package eu.benayoun.mymusicbrainz.data.repository


import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.ArtistCache
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.Release
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
    private val artistCache: ArtistCache,
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

    // UPDATE ARTIST
    override suspend fun updateAndSaveArtist(artistId: String) {
        externalScope.launch(dispatcher) {
            releasesMutex.withLock() {
                val response = musicBrainzDataSource.getReleases(artistId)
                _getArtistReleasesResponseFlow.value = response
                if (response is MusicBrainzGetArtistReleasesAPIResponse.Success) {
                    val foundArtist = getArtist(artistId)
                    if (!foundArtist.isEmpty()) {
                        val releases = response.releases
                        val artistToSave = Artist(
                            foundArtist.id,
                            foundArtist.name,
                            foundArtist.country,
                            foundArtist.type,
                            releases
                        )
                        artistCache.saveArtist(artistToSave)
                    }
                }
            }
        }
    }

    override suspend fun getArtistsReleasesFlow(artistId: String): Flow<List<Release>> =
        artistCache.getReleasesFlow(artistId)

    override suspend fun getLast3ArtistsConsultedFlow() = artistCache.getLast3ArtistsConsultedFlow()

    override suspend fun getArtist(artistId: String): Artist {
        //in db
        val savedArtist = artistCache.getArtist(artistId)
        if (savedArtist != null) return savedArtist
        else {
            // in searched artist
            val searchedArtistResponse = _searchArtistResponseFlow.value
            return if (searchedArtistResponse is MusicBrainzArtistSearchAPIResponse.Success) {
                searchedArtistResponse.artists.first { artist: Artist -> artist.id == artistId }
            } else Artist.EmptyArtist()
        }
    }

    /**
     * INTERNAL COOKING
     */

    private val releasesMutex = Mutex()
    private val _getArtistReleasesResponseFlow =
        MutableStateFlow<MusicBrainzGetArtistReleasesAPIResponse>(
            MusicBrainzGetArtistReleasesAPIResponse.Empty()
        )
}