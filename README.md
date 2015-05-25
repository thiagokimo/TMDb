# TMDb
Another of those Android REST clients.

## TL;DR
This project is an Android app which displays data from [The Movie Database](https://www.themoviedb.org) API.

Download the apk [here](https://github.com/thiagokimo/TMDb/blob/master/app-debug.apk?raw=true)

## The Mission
In this assignment I had to provide to users 3 main functionalities:

- Search for movies
- See details of a movie
- Open images of a movie

The following section explains how did I organized the architecture of my code.

## Architecture
The application is organized using a clean architecture approach, which consists in 2 main layers:

- Presentation (app)
- Domain

### Presentation Layer
All logic related with views were built here. The **Model-View-Presenter** pattern was used keeping all the displaying logic into the *presenters* as so away from the fragments and activities (which were considered only views). The presenters are composed with use-case that perform their tasks on background, outside the UI Thread, and returning the results through a callback into the views.

Third-party depencencies:
```
compile 'com.android.support:appcompat-v7:22.1.1'
compile 'com.android.support:recyclerview-v7:21.0.0'
compile 'com.android.support:cardview-v7:21.0.0'
compile 'com.squareup.picasso:picasso:2.5.2'
compile 'com.melnykov:floatingactionbutton:1.3.0'
compile 'com.ogaclejapan.smarttablayout:library:1.1.3@aar'
compile 'com.ogaclejapan.smarttablayout:utils-v4:1.1.3@aar'
compile 'com.mcxiaoke.photoview:library:1.2.3'
compile 'com.pnikosis:materialish-progress:1.5'
compile('com.crashlytics.sdk.android:crashlytics:2.2.4@aar') { transitive = true; }
```

The **AppCompat** was used let the app UI's look'n feel as much Material Design as possible, with the hold of **CardView**, **Materialish Progress** and **Floating Action Button**. The **RecyclerView** was used to handle all collections displayed in the app. All loaded images from the web were handled with **Picasso**, which let us deal with assynchrounous image downloading. Interfaces with tabs were build with **SmartTabLayout**. The fullscreen image where displayer with the **PhotoView** library, which allow users to zoom in/out and move around the image. The **Crashlytics** library where added in order to keep tracks of all non predictable crashes, so that I could avoid figuring out how to reproduce those kinds of bugs.

### Domain Layer
This layer have all business rules. All use-cases implementations, server-side communication and entity objects were implemented here. This layer doesn't know the existance of the presentation layer.

Third-party dependencies:
```
compile 'com.path:android-priority-jobqueue:1.1.2'
compile 'com.squareup.retrofit:retrofit:1.9.0'
```

The **Retrofit** library was used to do our http calls. The **Priority Job Queue** was used to wrap all our usecases in a thread-safe environment.

## Notes

- In order to solve the request search limit I called the search movie usecase 1 second after the user typed in the search input.
- Since there are many different kinds of image sizes, I displayer lower-sized images in my recyler views and bugger or original sizes of images in the detailed screens.
- I didn't write tests. If I did that I would test my presenters instead of my views. 

## Author
Thiago Rocha

+553184349266
