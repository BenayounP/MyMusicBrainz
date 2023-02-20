package eu.benayoun.mymusicbrainz.data.dblocalsource.artist

import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.Release
import kotlinx.coroutines.flow.Flow

interface ArtistCache {
    suspend fun getLast3ArtistsConsultedFlow(): Flow<List<Artist>>
    suspend fun saveArtist(artist: Artist)
    suspend fun getArtist(artistId: String): Artist?
    suspend fun getReleasesFlow(artistId: String): Flow<List<Release>>
}