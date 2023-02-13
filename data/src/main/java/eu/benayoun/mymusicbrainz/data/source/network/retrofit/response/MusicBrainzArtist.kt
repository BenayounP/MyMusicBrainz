package eu.benayoun.mymusicbrainz.data.source.network.retrofit.response

import com.google.gson.annotations.SerializedName
import eu.benayoun.mymusicbrainz.data.model.Artist
import eu.benayoun.mymusicbrainz.data.source.network.retrofit.Area


data class MusicBrainzArtist(
    @SerializedName("id") var id: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("score") var score: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sort-name") var sortName: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("area") var area: Area? = Area(),
    @SerializedName("begin-area") var beginArea: BeginArea? = BeginArea(),
    @SerializedName("disambiguation") var disambiguation: String? = null,
    @SerializedName("life-span") var lifeSpan: LifeSpan? = LifeSpan()
) {
    fun asArtist(): Artist {
        return Artist(id ?: "", name ?: "", country ?: "")
    }
}