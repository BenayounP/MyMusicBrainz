package eu.benayoun.mymusicbrainz.data.artistsearch.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


internal data class LifeSpan(

    @SerializedName("begin") var begin: String? = null,
    @SerializedName("ended") var ended: Boolean? = null,
    @SerializedName("end") var end: String? = null

)