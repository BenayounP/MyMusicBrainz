package eu.benayoun.mymusicbrainz.data.model.apiresponse

import eu.benayoun.mymusicbrainz.data.model.apiresponse.global.MusicBrainzAPIError

sealed class MusicBrainzAPIResponse<SUCCESS_CONTENT> {
    // special case when no search have been made
    class Empty<SUCCESS_CONTENT>() : MusicBrainzAPIResponse<SUCCESS_CONTENT>()

    // Success -> succes content (artist list or recordings...)
    class Success<SUCCESS_CONTENT>(val successContent: SUCCESS_CONTENT) :
        MusicBrainzAPIResponse<SUCCESS_CONTENT>()

    // Error with explanation of origin
    class Error<SUCCESS_CONTENT>(val musicBrainzAPIError: MusicBrainzAPIError) :
        MusicBrainzAPIResponse<SUCCESS_CONTENT>()
}