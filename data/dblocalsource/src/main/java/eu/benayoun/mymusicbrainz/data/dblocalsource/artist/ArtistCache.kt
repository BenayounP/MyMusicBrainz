package eu.benayoun.mymusicbrainz.data.dblocalsource.artist

import eu.benayoun.mymusicbrainz.data.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistCache {
    suspend fun getLast3ArtistsConsultedFlow(): Flow<List<Artist>>
    suspend fun saveArtist(artist: Artist)
}