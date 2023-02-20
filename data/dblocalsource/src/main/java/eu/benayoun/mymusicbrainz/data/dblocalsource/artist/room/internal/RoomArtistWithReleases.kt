package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room.internal

import androidx.room.Embedded
import androidx.room.Relation
import eu.benayoun.mymusicbrainz.data.model.Artist

internal data class RoomArtistWithReleases(
    @Embedded val artistEntity: ArtistEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "artistId"
    )
    val releasesEntity: List<ReleaseEntity>
) {
    fun asArtist(): Artist {
        return Artist(
            artistEntity.id,
            artistEntity.name,
            artistEntity.country,
            artistEntity.type,
            releasesEntity.map { it.asRelease() })
    }
}