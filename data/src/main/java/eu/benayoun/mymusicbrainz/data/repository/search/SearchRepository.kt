package eu.benayoun.mymusicbrainz.data.repository.search

import eu.benayoun.mymusicbrainz.data.model.MusicBrainzArtistSearchAPIResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    // SEARCH
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)
}