package eu.benayoun.mymusicbrainz.data.artistsearch.repository

import eu.benayoun.mymusicbrainz.data.artistsearch.model.MusicBrainzArtistSearchAPIResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    // SEARCH
    suspend fun getSearchArtistResponseFlow(): Flow<MusicBrainzArtistSearchAPIResponse>
    fun searchArtist(query: String)
}