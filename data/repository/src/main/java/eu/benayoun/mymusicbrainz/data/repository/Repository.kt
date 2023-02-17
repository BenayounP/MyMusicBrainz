package eu.benayoun.mymusicbrainz.data.repository

import eu.benayoun.mymusicbrainz.data.model.apiresponse.MusicBrainArtistSearchAPIResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    // SEARCH
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainArtistSearchAPIResponse>
    fun searchArtist(query: String)
}