# CoroutinesTesting

**This is Work In Progress**

A sample app to showcase the basics of using **MVVM architecture** in an Android Project. Uses **Coroutines**. Written in **Kotlin**. Uses The **Star Wars API**, or "swapi" as the backend

![digital_sky_hld](app/src/main/res/mipmap-xxxhdpi/digital_sky_hld.png)


**Presentation layer** that contains a fragment (View) and a ViewModel per feature

**Domain layer** that uses Use-Cases for business logic

**Data layer** with a repository and a data source (Server) that are queried with one-shot operations (no listeners)



# Unit Testing(Based on strategies explained in https://github.com/android/architecture-components-samples/tree/master/GithubBrowserSample/)

**ViewModel Tests**
Each ViewModel is tested using local unit tests with mock Usecase implementations.

**Usecase Tests**
Each Usecase is tested using local unit tests with mock Repository implementations.

**Repository Tests**
Each Repository is tested using local unit tests with mock web service and mock database.

**Webservice Tests**
The project uses MockWebServer project to test REST api interactions.
