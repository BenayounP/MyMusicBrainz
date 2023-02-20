package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.*
import eu.benayoun.mymusicbrainz.data.model.Artist
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ArtistDao {
    // Insert a new artist into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: ArtistEntity)

    // Insert a new release into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRelease(release: ReleaseEntity)

    @Transaction
    suspend fun saveArtistWithReleases(artist: Artist) {
        insertArtist(ArtistEntity(artist))

        for (release in artist.releases) {
            insertRelease(ReleaseEntity(release, artist.id))
        }
    }

    @Query("DELETE FROM artists WHERE id NOT IN (SELECT id FROM artists ORDER BY created_at DESC LIMIT 3)")
    fun deleteOldestArtists()

    @Query("DELETE FROM releases WHERE artistId NOT IN (SELECT id FROM artists ORDER BY created_at DESC LIMIT 3)")
    fun deleteReleasesForOldestArtist()

    // Retrieve the most recent 3 artists with their associated releases
    @Transaction
    @Query("SELECT * FROM artists ORDER BY created_at DESC LIMIT 3")
    fun getLast3artists(): Flow<List<RoomArtistWithReleases>>

    // retrieve an artistById
    @Query("SELECT * FROM artists WHERE id = :artistId")
    suspend fun getArtist(artistId: String): RoomArtistWithReleases?
}