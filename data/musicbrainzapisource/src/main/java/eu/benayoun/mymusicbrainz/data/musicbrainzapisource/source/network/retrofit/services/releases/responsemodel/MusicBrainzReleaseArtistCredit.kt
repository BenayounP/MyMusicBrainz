package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class MusicBrainzReleaseArtistCredit(

    @SerializedName("name") var name: String? = null,
    @SerializedName("artist") var musicBrainzReleaseArtist: MusicBrainzReleaseArtist? = MusicBrainzReleaseArtist()

)