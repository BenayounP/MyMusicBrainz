package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.responsemodel.Area


internal data class ReleaseEvents(
    @SerializedName("date") var date: String? = null,
    @SerializedName("area") var area: Area? = Area()

)