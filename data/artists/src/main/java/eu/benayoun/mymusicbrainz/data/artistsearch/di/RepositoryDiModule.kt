package eu.benayoun.mymusicbrainz.data.artistsearch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.benayoun.mymusicbrainz.data.artistsearch.repository.DefaultSearchRepository
import eu.benayoun.mymusicbrainz.data.artistsearch.repository.SearchRepository
import eu.benayoun.mymusicbrainz.data.artistsearch.source.network.MusicBrainzDataSource
import eu.benayoun.mymusicbrainz.data.artistsearch.source.network.di.MusicBrainzDataSourceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryProvider

@Module
@InstallIn(SingletonComponent::class)
class RepositoryDiModule {
    @RepositoryProvider
    @Singleton
    @Provides
    internal fun providesRepository(
        @MusicBrainzDataSourceProvider musicBrainzDataSource: MusicBrainzDataSource
    ): SearchRepository =
        DefaultSearchRepository(musicBrainzDataSource, MainScope(), Dispatchers.IO)
}