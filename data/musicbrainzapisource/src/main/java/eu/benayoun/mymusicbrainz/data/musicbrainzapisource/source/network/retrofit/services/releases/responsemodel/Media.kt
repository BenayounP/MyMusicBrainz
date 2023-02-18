package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class Media(

    @SerializedName("format") var format: String? = null,
    @SerializedName("disc-count") var discCount: Int? = null,
    @SerializedName("track-count") var trackCount: Int? = null

)