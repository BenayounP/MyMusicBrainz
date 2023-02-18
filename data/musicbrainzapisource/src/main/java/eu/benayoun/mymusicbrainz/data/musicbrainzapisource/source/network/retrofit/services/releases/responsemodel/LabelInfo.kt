package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class LabelInfo(

    @SerializedName("catalog-number") var catalogNumber: String? = null,
    @SerializedName("label") var label: Label? = Label()

)