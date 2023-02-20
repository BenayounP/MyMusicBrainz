package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.benayoun.mymusicbrainz.data.model.Artist

@Entity(tableName = "artists")
internal data class ArtistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val country: String,
    val type: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {
    constructor(artist: Artist) : this(
        id = artist.id,
        name = artist.name,
        country = artist.country,
        type = artist.type,
        createdAt = System.currentTimeMillis()
    )
}