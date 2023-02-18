package eu.benayoun.mymusicbrainz.data.repository

import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzGetArtistReleasesAPIResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    // SEARCH ARTIST
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)

    // COMPLETE ARTIST DATA WITH RELEASES
    suspend fun getArtistReleasesResponseFlow(): Flow<MusicBrainzGetArtistReleasesAPIResponse>
    fun getReleases(arid: String)
}