package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room

import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.ArtistCache
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.ArtistDao
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.ArtistEntity
import eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal.ReleaseEntity
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.model.Release
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomArtistCache(private val artistDao: ArtistDao) : ArtistCache {

    override suspend fun getLast3ArtistsConsultedFlow(): Flow<List<Artist>> =
        artistDao.get3RecentArtistsWithReleases().map {
            it.map { it.asArtist() }
        }

    override suspend fun saveArtist(artist: Artist) {
        artistDao.insertArtist(ArtistEntity(artist))
        artist.releases.forEach { release: Release ->
            artistDao.insertRelease(ReleaseEntity(release, artist.id))
        }
        artistDao.deleteOldestArtists()
        artistDao.deleteReleasesForOldestArtist()
    }
}