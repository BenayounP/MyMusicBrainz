package eu.benayoun.mymusicbrainz.data.repository

import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    // SEARCH ARTIST
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)
    suspend fun getSearchedArtist(artistId: String): Artist


    // COMPLETE ARTIST DATA WITH RELEASES
    suspend fun getArtistReleasesResponseFlow(artistId: String): Flow<MusicBrainzGetArtistReleasesAPIResponse>
}