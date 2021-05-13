# Chuck Noris Facts

Android app using [Chuck Norris API](https://api.chucknorris.io/) to exemplify Clean Architecture with MVVM pattern and Repositories. Use of the Chuck Norris API does not endorese the jokes at all (they are pretty bad and dated).

Project written in Kotlin, utilizing the latest Android Jetpack libraries for support, like the MaterialComponents theme, Navigation, Room and ViewModel.

Check [Projects tab here in Github](https://github.com/vgchabelman/chuck-norris-android/projects)  to see current progress.

## Libraries used
- Requests: [Retrofit](https://square.github.io/retrofit/)
- Images lazy loading: [Coil](https://github.com/coil-kt/coil)
- Persistance Storage: [Room](https://developer.android.com/jetpack/androidx/releases/room)
- Dependecy Injection: [Dagger2](https://dagger.dev/)
- Asynchronous Calls: [RxJava 2](https://github.com/ReactiveX/RxJava/tree/2.x)
- Unit Tests: [JUnit 4](https://junit.org/junit4/javadoc/latest/overview-summary.html)
- Mocking Tests: [MockK](https://mockk.io/)

## Notes
- Added offline support through favorite jokes
- Used the new ViewBinding library instead of the soon to be discontinued Kotlin Synthetics
- 1.5 second debounce on the Search widget to avoid API throttling
- Use of [DiffUtil](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil) to animate lists with minimal effort
