package eu.benayoun.mymusicbrainz.data.model

sealed class MusicBrainzArtistSearchAPIResponse() {
    class Success(val artistsList: List<Artist>) : MusicBrainzArtistSearchAPIResponse()
    class Error(val musicBrainzAPIError: MusicBrainzAPIError) : MusicBrainzArtistSearchAPIResponse()

}