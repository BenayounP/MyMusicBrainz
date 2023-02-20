package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.benayoun.mymusicbrainz.data.model.Release

@Entity(tableName = "releases")
internal data class ReleaseEntity(
    @PrimaryKey val id: String,
    val artistId: String,
    val title: String,
    val date: String,
) {
    constructor(release: Release, artistId: String) : this(
        id = release.id,
        title = release.title,
        date = release.date,
        artistId = artistId
    )

    fun asRelease(): Release = Release(id, title, date)
}