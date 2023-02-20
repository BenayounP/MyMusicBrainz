package eu.benayoun.mymusicbrainz.data.model

data class Artist(
    val id: String,
    val name: String,
    val country: String,
    val type: String,
    val releases: List<Release> = listOf()
) {
    companion object {
        fun EmptyArtist(): Artist = Artist("?", "?", "?", "?")
    }

    fun isEmpty() = id == "?"
}