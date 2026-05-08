# TopSports

Android app built as a final project for the Android lab. The idea came from wanting to do something sport-related — basically an app where you can set up a profile as an athlete and browse sports and leagues from around the world.

## What it does

There are two main parts. The first one is the profile side — you register with your details (name, age, city, sport you practice), and you can edit your profile whenever you want. The second part is the sports list which pulls data from a public API, TheSportsDB, and if you tap on a sport you can also see the available leagues for it.

## How to run it

You need Android Studio installed. Open the project folder via File > Open, wait for Gradle sync to finish (can take a few minutes the first time), then hit Run either on an emulator or a physical device with USB debugging enabled.

One issue I ran into: if you get an error about `allprojects repositories`, just remove that block from the root `build.gradle`. Another common one is the JVM version — if it asks, go with JVM 17.

## Structure

I tried to keep the code organized around MVVM as much as I could:

```
ui/           - activities and fragments
data/
  local/      - Room DB (entity, dao, database)
  remote/     - Retrofit (api service, models, client)
  repository/ - bridges local and remote
adapter/      - RecyclerView adapter for the sports list
utils/        - SessionManager for SharedPreferences
```

## Tech used

- Kotlin
- Room for local database
- Retrofit + Gson for HTTP requests
- Navigation Component for screen navigation
- ViewModel + LiveData
- Coroutines for async operations
- SharedPreferences for user session
- Glide for loading images
- Material Design components

## API

TheSportsDB — free and no API key needed for the basic endpoints.

- `GET /api/v1/json/3/all_sports.php` — returns the full list of sports
- `GET /api/v1/json/3/search_all_leagues.php?s={sport}` — returns leagues for a given sport

<img width="2388" height="1452" alt="image" src="https://github.com/user-attachments/assets/0b51131d-5d7a-4681-9324-4c09a22910ec" />

