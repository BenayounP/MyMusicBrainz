package eu.benayoun.mymusicbrainz.data.repository

import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainzArtistSearchAPIResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    // SEARCH ARTIST
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)

    // GET ARTIST
    // first in saved ones and after on current searched ones
    suspend fun getArtist(artistId: String): Artist

    // Update Artist Releases
    suspend fun updateArtistReleases(artistId: String)


    // SAVE ARTISTS
    suspend fun getLast3ArtistsConsultedFlow(): Flow<List<Artist>>
}