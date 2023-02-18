package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.artistsearch.responsemodel

import com.google.gson.annotations.SerializedName


internal data class MusicBrainzArtistSearchResponse(

    @SerializedName("created") var created: String? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("artists") var artists: ArrayList<MusicBrainzArtist> = arrayListOf()

)