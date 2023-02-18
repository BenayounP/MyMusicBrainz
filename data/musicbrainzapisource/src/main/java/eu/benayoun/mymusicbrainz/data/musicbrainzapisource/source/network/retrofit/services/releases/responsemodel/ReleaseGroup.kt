package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class ReleaseGroup(

    @SerializedName("id") var id: String? = null,
    @SerializedName("type-id") var typeId: String? = null,
    @SerializedName("primary-type-id") var primaryTypeId: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("primary-type") var primaryType: String? = null

)