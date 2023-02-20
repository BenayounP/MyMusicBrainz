package eu.benayoun.mymusicbrainz.data.dblocalsource.artist.room

import android.content.Context
import eu.benayoun.mymusicbrainz.data.model.BuildConfig
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [BuildConfig.MIN_SDK_VERSION])
abstract class AndroidLocalTest {
    val applicationContext: Context by lazy {
        RuntimeEnvironment.getApplication()
    }
}