# MyMusicBrainz

A tiny app that get some informations on artist
via [Music Brainz API](https://musicbrainz.org/doc/MusicBrainz_API)

## Markdown tip

Some difficulties to read markdown files on Android
studio? [Fix it in less than 3 minutes](https://joachimschuster.de/posts/android-studio-fix-markdown-plugin-again/)
.

# Features

* Search artist and display details about its releases
* Save the last 3 artist consulted
* (ugly) light and dark themes

# Focus

I focused primarily on :

* The use of the latest tools and best practices from Google
* Multi module architecture

# Tools

### Global Architecture

[Android app architecture](https://developer.android.com/topic/architecture)

### Language

[Kotlin](https://developer.android.com/kotlin)

### Data Stream

[Flow](https://developer.android.com/kotlin/flow)

### Dependency Injection

[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

### Data

* Fetching data on API: [Retrofit](https://square.github.io/retrofit/)
* Repository
  * Data Base: [Room](https://developer.android.com/jetpack/androidx/releases/room)

### UI

[Compose](https://developer.android.com/jetpack/compose)

### Tests

* Simulate device for local tests: [Robolectric](http://robolectric.org/)
* Assert tooling: [Google Truth](https://github.com/google/truth)

# Some thoughts and improvements

* Due to lack of time
  * There is only locals tests (for the DB) and Few documentation
    * Better tests, especially UI ones, and documentation can be seen in my personal project:
      https://github.com/BenayounP/AndroidMovieDataBase
* Text is hardcoded -> Use strings.xml
* Remove okHttp interceptor that log all calls details should be done

