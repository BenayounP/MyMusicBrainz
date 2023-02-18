package eu.benayoun.mymusicbrainz.data.model.apiresponse

import eu.benayoun.mymusicbrainz.data.model.Release
import eu.benayoun.mymusicbrainz.data.model.apiresponse.global.MusicBrainzAPIError

sealed class MusicBrainzGetArtistReleasesAPIResponse : MusicBrainzAPIResponse<Release>() {
    // special case when no request have been made
    class Empty : MusicBrainzGetArtistReleasesAPIResponse()

    // Success -> the list of recordings
    class Success(val releases: List<Release>) : MusicBrainzGetArtistReleasesAPIResponse()

    // Error with explanation of origin
    class Error(val musicBrainzAPIError: MusicBrainzAPIError) :
        MusicBrainzGetArtistReleasesAPIResponse()
}