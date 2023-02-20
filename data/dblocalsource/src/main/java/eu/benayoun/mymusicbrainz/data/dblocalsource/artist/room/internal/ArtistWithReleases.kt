package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.Embedded
import androidx.room.Relation
import eu.benayoun.mymusicbrainz.data.model.Artist

internal data class ArtistWithReleases(
    @Embedded val artist: ArtistEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "artist_id"
    )
    val releases: List<ReleaseEntity>
) {
    fun asArtist(): Artist {
        return Artist(
            artist.id,
            artist.name,
            artist.country,
            artist.type,
            releases.map { it.asRelease() })
    }
}