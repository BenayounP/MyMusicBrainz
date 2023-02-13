package eu.benayoun.mymusicbrainz.data.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


data class BeginArea(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sort-name") var sortName: String? = null

)