package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room

import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.ArtistCache
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.ArtistDao
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.Release
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomArtistCache(private val artistDao: ArtistDao) : ArtistCache {

    override suspend fun getLast3ArtistsConsultedFlow(): Flow<List<Artist>> =
        artistDao.getLast3artists().map {
            it.map { it.asArtist() }
        }

    override suspend fun saveArtist(artist: Artist) {
        artistDao.saveArtistWithReleases(artist)
        artistDao.deleteReleasesForOldestArtist()
        artistDao.deleteOldestArtists()
    }

    override suspend fun getArtist(artistId: String): Artist? {
        val artistEntity = artistDao.getArtist(artistId)
        return if (artistEntity != null) artistEntity.asArtist()
        else null
    }

    override suspend fun getReleasesFlow(artistId: String): Flow<List<Release>> {
        return artistDao.getReleases(artistId).map {
            it.map { it.asRelease() }
        }
    }
}