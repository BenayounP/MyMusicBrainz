package eu.benayoun.mymusicbrainz.data.repository.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


internal data class BeginArea(

    @SerializedName("disambiguation") var disambiguation: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("type-id") var typeId: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("sort-name") var sortName: String? = null

)