# TopSports

Aplicatie Android dezvoltata ca proiect final pentru laboratorul de Android. Ideea a venit din faptul ca voiam ceva legat de sport, mai exact o aplicatie unde poti sa iti faci un profil ca sportiv si sa explorezi sporturi si ligi din toata lumea.

## Ce face aplicatia

Practic sunt doua parti principale. Prima e partea de profil — te inregistrezi cu datele tale (nume, varsta, oras, sportul pe care il practici), si dupa iti poti edita oricand profilul. A doua parte e lista de sporturi care vine dintr-un API public, TheSportsDB, si daca apesi pe un sport vezi si ligile disponibile pentru el.

## Cum rulezi proiectul

Ai nevoie de Android Studio instalat. Deschizi folderul proiectului din File > Open, astepti sa faca Gradle sync (poate dura cateva minute prima data), si dupa dai Run fie pe un emulator fie pe un telefon fizic cu USB debugging activat.

O problema intalnita: daca apare eroarea cu `allprojects repositories`, stergi blocul ala din `build.gradle`-ul de la root. Alta problema frecventa e cu JVM-ul — daca te intreaba, alegi JVM 17.

## Structura

Am incercat sa tin codul organizat dupa MVVM cat de bine am putut:

```
ui/         - activitati si fragmente
data/
  local/    - Room DB (entity, dao, database)
  remote/   - Retrofit (api service, modele, client)
  repository/ - leaga local cu remote
adapter/    - RecyclerView adapter pentru lista de sporturi
utils/      - SessionManager pentru SharedPreferences
```

## Tech folosit

- Kotlin
- Room pentru baza de date locala
- Retrofit + Gson pentru requesturile HTTP
- Navigation Component pentru navigarea intre ecrane
- ViewModel + LiveData
- Coroutines pentru operatii async
- SharedPreferences pentru sesiunea utilizatorului
- Glide pentru imaginile din lista
- Material Design components

## API

TheSportsDB — e gratuit si nu necesita API key pentru endpointurile de baza.

- `GET /api/v1/json/3/all_sports.php` — returneaza lista tuturor sporturilor
- `GET /api/v1/json/3/search_all_leagues.php?s={sport}` — returneaza ligile pentru un sport anume
