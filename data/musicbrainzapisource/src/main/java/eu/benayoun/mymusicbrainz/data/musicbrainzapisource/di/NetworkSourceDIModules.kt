package eu.benayoun.mymusicbrainz.data.repository.source.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mymusicbrainz.data.musicbrainzapisource.source.network.retrofit.artistsearch.RetrofitMusicBrainzAPISource
import eu.benayoun.mymusicbrainz.data.repository.source.network.MusicBrainzAPISource
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MusicBrainzAPISourceProvider

@Module
@InstallIn(SingletonComponent::class)
class NetworkSourceDIModules {
    @MusicBrainzAPISourceProvider
    @Singleton
    @Provides
    fun providesMusicBrainzAPISource(@ApplicationContext context: Context): MusicBrainzAPISource {
        return RetrofitMusicBrainzAPISource(context)
    }
}