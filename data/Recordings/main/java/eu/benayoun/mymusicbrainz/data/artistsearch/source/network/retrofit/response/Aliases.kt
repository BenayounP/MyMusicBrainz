package eu.benayoun.mymusicbrainz.data.artistsearch.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


internal data class Aliases(

    @SerializedName("sort-name") var sortName: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("begin") var begin: String? = null,
    @SerializedName("ended") var ended: Boolean? = null,
    @SerializedName("primary") var primary: String? = null,
    @SerializedName("type-id") var typeId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("end") var end: String? = null,
    @SerializedName("locale") var locale: String? = null

)