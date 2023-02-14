package eu.benayoun.mymusicbrainz.data.source.local.artists

import eu.benayoun.mymusicbrainz.data.model.Artist
import kotlinx.coroutines.flow.Flow


interface ArtistsCache {

    // get the flow with saved artists
    suspend fun getSavedArtistsFlow(): Flow<List<Artist>>

    //save artist
    suspend fun saveArtist(artist: Artist)

}