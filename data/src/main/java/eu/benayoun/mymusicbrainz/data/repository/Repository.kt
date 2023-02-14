package eu.benayoun.mymusicbrainz.data.repository

import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    // SEARCH
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)
}