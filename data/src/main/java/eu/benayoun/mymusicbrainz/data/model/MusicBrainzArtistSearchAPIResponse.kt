package eu.benayoun.mymusicbrainz.data.model

sealed class MusicBrainzArtistSearchAPIResponse() {
    // special case when no search have been made
    class Empty() : MusicBrainzArtistSearchAPIResponse()

    // Success -> the list of artist found
    class Success(val artistsList: List<Artist>) : MusicBrainzArtistSearchAPIResponse()

    // Error with explanation of origin
    class Error(val musicBrainzAPIError: MusicBrainzAPIError) : MusicBrainzArtistSearchAPIResponse()

}