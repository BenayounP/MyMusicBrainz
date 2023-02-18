package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class Label(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null

)