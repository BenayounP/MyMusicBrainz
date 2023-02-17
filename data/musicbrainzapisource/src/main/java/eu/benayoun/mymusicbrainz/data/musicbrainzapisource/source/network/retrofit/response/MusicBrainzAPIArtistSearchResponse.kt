package eu.benayoun.mymusicbrainz.data.artistsearch.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


internal data class MusicBrainzArtistSearchResponse(

    @SerializedName("created") var created: String? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("offset") var offset: Int? = null,
    @SerializedName("artists") var artists: ArrayList<MusicBrainzArtist> = arrayListOf()

)