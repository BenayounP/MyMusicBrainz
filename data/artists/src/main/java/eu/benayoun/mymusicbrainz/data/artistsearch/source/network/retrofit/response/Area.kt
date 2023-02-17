package eu.benayoun.mymusicbrainz.data.artistsearch.source.network.retrofit.response

import com.google.gson.annotations.SerializedName


internal data class Area(

    @SerializedName("sort-name") var sortName: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("type-id") var typeId: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("disambiguation") var disambiguation: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("iso-3166-1-codes") var isoCodes: ArrayList<String> = arrayListOf()

)