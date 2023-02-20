package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ArtistDao {
    // Insert a new artist into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(artist: ArtistEntity)

    // Insert a new release into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRelease(release: ReleaseEntity)

    @Query("DELETE FROM artists WHERE id NOT IN (SELECT id FROM artists ORDER BY created_at DESC LIMIT 3)")
    fun deleteOldestArtists()

    @Query("DELETE FROM releases WHERE artist_id IN (SELECT id FROM artists ORDER BY created_at ASC LIMIT 1)")
    fun deleteReleasesForOldestArtist()

    // Retrieve the most recent 3 artists with their associated releases
    @Transaction
    @Query("SELECT * FROM artists ORDER BY created_at DESC LIMIT 3")
    fun get3RecentArtistsWithReleases(): Flow<List<ArtistWithReleases>>
}