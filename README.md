# tv-series-demo

## Features

- 100% **Kotlin** code!
- **MVVM** design pattern
- Reactive approach and state management with **RxJava2** / **RxAndroid** / **RxBinding** (thanks Jake Wharton!)
- DI with **Dagger2**
- Architecture Components (**ViewModel**)
- **Retrofit2** for consuming TMDB REST API
- Facebook's **Fresco** for image loading & caching
- **Mockito** & **JUnit** for unit testing

The architecture has been based on this series on state management with RxJava by Square's Jake Wharton http://jakewharton.com/the-state-of-managing-state-with-rxjava/

#### Notes

- Different names have been given to this approach / architecture. Some have called it Redux, while others refer to it as MVI (**M**odel-**V**iew-**Intent**), since this pattern seems to have been inspired on a JS framework. Others simply refer to it as a fully reactive approach with MVVM thanks to RxJava

- I took the time to implement a simple search functionality as an extra feature. I am aware that this was not requested but it seemed like a nice extra. **This has not been unit tested**
