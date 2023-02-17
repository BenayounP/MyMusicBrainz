package eu.benayoun.mymusicbrainz.data.artistsearch.source.network.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mymusicbrainz.data.artistsearch.source.network.MusicBrainzDataSource
import eu.benayoun.mymusicbrainz.data.artistsearch.source.network.retrofit.RetrofitMusicBrainzDataSource
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MusicBrainzDataSourceProvider

@Module
@InstallIn(SingletonComponent::class)
class NetworkSourceDIModules {
    @MusicBrainzDataSourceProvider
    @Singleton
    @Provides
    internal fun providesMusicBrainzDataSource(@ApplicationContext context: Context): MusicBrainzDataSource {
        return RetrofitMusicBrainzDataSource(context)
    }
}