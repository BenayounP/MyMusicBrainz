package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import eu.benayoun.mymusicbrainz.data.model.Release

@Entity(
    tableName = "releases",
    foreignKeys = [
        ForeignKey(
            entity = ArtistEntity::class,
            parentColumns = ["id"],
            childColumns = ["artist_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

internal data class ReleaseEntity(
    @PrimaryKey val id: String,
    val title: String,
    val date: String,
    @ColumnInfo(name = "artist_id", index = true) val artistId: String
) {
    constructor(release: Release, artistId: String) : this(
        id = release.id,
        title = release.title,
        date = release.date,
        artistId = artistId
    )

    fun asRelease(): Release = Release(id, title, date)
}