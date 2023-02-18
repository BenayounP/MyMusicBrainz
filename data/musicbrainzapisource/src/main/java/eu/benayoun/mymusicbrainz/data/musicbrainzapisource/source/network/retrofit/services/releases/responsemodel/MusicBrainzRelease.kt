package eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.services.releases.responsemodel

import com.google.gson.annotations.SerializedName
import eu.benayoun.mymusicbrainz.data.model.Release


internal data class MusicBrainzRelease(

    @SerializedName("id") var id: String? = null,
    @SerializedName("score") var score: Int? = null,
    @SerializedName("status-id") var statusId: String? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("text-representation") var textRepresentation: TextRepresentation? = TextRepresentation(),
    @SerializedName("artist-credit") var artistCredit: ArrayList<MusicBrainzReleaseArtistCredit> = arrayListOf(),
    @SerializedName("release-group") var releaseGroup: ReleaseGroup? = ReleaseGroup(),
    @SerializedName("date") var date: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("release-events") var releaseEvents: ArrayList<ReleaseEvents> = arrayListOf(),
    @SerializedName("barcode") var barcode: String? = null,
    @SerializedName("asin") var asin: String? = null,
    @SerializedName("label-info") var labelInfo: ArrayList<LabelInfo> = arrayListOf(),
    @SerializedName("track-count") var trackCount: Int? = null,
    @SerializedName("media") var media: ArrayList<Media> = arrayListOf()
) {
    fun asRelease(): Release = Release(id ?: "?", title ?: "?", date ?: "?")

}