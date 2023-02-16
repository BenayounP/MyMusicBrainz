package eu.benayoun.mymusicbrainz.data.source.network.retrofit.response

import com.google.gson.annotations.SerializedName
import eu.benayoun.mymusicbrainz.data.model.Artist



internal data class MusicBrainzArtist(
    @SerializedName("life-span") var lifeSpan: LifeSpan? = LifeSpan(),
    @SerializedName("begin_area") var beginArea: BeginArea? = BeginArea(),
    @SerializedName("country") var country: String? = null,
    @SerializedName("area") var area: Area? = Area(),
    @SerializedName("aliases") var aliases: ArrayList<Aliases> = arrayListOf(),
    @SerializedName("isnis") var isnis: ArrayList<String> = arrayListOf(),
    @SerializedName("end_area") var endArea: String? = null,
    @SerializedName("type-id") var typeId: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("gender-id") var genderId: String? = null,
    @SerializedName("ipis") var ipis: ArrayList<String> = arrayListOf(),
    @SerializedName("disambiguation") var disambiguation: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("sort-name") var sortName: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("gender") var gender: String? = null
) {
    fun asArtist(): Artist {
        return Artist(id ?: "?", name ?: "?", country ?: "?", type = type ?: "")
    }
}