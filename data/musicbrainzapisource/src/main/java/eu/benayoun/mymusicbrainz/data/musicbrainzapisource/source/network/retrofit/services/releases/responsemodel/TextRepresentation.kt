package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName


internal data class TextRepresentation(

    @SerializedName("language") var language: String? = null,
    @SerializedName("script") var script: String? = null

)