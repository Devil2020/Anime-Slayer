# Anime App [Design Templete Only in this branch]

Hi my name is Mohammed morse , this branch only have the templete of the Anime for any interview tasks only



# Anime App
## Let's explore Architecture Components

According to  [Android Documentation](https://developer.android.com/topic/libraries/architecture), Architecture Components are a set of Android libraries for structuring your app in a way that is robust, testable, and maintainable

[![](https://github.com/mohammedgmgn/MovieApp-Clean-Architecture/raw/master/arccomponents.png)](https://github.com/mohammedgmgn/MovieApp-Clean-Architecture/blob/master/arccomponents.png)

## [](https://github.com/Devil2020/Anime-Slayer#ui-controllers)UI Controllers

are activities or fragments. The only job of UI controllers is to know how to display data and pass on UI events, such as the user pressing a button. UI Controllers neither contain the UI data, nor directly manipulate data.

## [](https://github.com/Devil2020/Anime-Slayer#viewmodels-and-livedata)ViewModels and LiveData

These classes represent all of the data needed for the UI to display. You'll learn exactly how these two classes function together in this Project.

## [](https://github.com/Devil2020/Anime-Slayer#repository)Repository

This class is the single source of truth for all of our app's data and acts as a clean API for the UI to communicate with. ViewModels simply request data from the repository. They do not need to worry about whether the repository should load from the database or network, or how or when to persist the data. The repository manages all of this. As part of this responsibility, the repository is a mediator between the different data sources.

## [](https://github.com/Devil2020/Anime-Slayer#remote-network-data-source)Remote Network Data Source

Manages data from a remote data source, such as the internet.

Model - Manages local data stored in the database.

## [](https://github.com/Devil2020/Anime-Slayer#remote-network-data-source)Okay now this is my arhitecture will be :


[![](https://i.ibb.co/5BTMtSx/Anime-Slayer-Architecture.jpg)](https://i.ibb.co/5BTMtSx/Anime-Slayer-Architecture.jpg)

### [](https://github.com/Devil2020/Anime-Slayer#used-libraries)Used libraries:

-   [Fuel](https://github.com/ReactiveX/RxJava)
    
-   [Retrofit](https://github.com/ReactiveX/RxAndroid)
    
-   [Fast Networking](https://github.com/codepath/android_guides/wiki/Dependency-Injection-with-Dagger-2)
    
-   [Room](https://github.com/square/retrofit)
    
-   [Android architecture components](https://developer.android.com/topic/libraries/architecture/index.html)
    
-   [Realm](https://developer.android.com/topic/libraries/architecture/room.html)
    
-   [Hilt](https://github.com/coil-kt/coil)
    
-   [JUnit](https://github.com/coil-kt/coil)
    
-   [Espresso](https://github.com/coil-kt/coil)
    
-   [AndroidX](https://github.com/coil-kt/coil)
    
-   [Truth](https://github.com/coil-kt/coil)
    

### [](https://github.com/mohammedgmgn/MovieApp-Clean-Architecture#refrences)Refrences:

-   [ِAndroid Documentation](https://developer.android.com/topic/libraries/architecture)
-   [source code for the official Google I/O 2018 for Android app](https://android-developers.googleblog.com/2018/08/google-releases-source-for-google-io.html)
-   [Architecture Components Code Lab](https://codelabs.developers.google.com/codelabs/build-app-with-arch-components/index.html?index=..%2F..%2Findex#0)

### [](https://github.com/Devil2020/Anime-Slayer#license)License:
Copyright 2021 Mohammed Morse

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
