package eu.benayoun.mymusicbrainz.data.source.network.retrofit

import com.google.gson.annotations.SerializedName
internal data class Area(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sort-name") var sortName: String? = null
)