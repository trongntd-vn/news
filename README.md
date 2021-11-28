# News
- This app will fetch News provide by https://newsapi.org then display
- Cache data to file for supporting offline mode.
- Some architects in this project:
  - MVVM
  - Hilt
  - Repository pattern
  - Navigation
  - Coroutine
  - Retrofit + OkHttp + Gson

# Install
- Create file `local.properties` in root folder.
```
SERVER_URL=https://newsapi.org/v2/
API_KEY=[Your key]
```
- If you got error `required java 11 for android gradle plugin` : Please set JAVA_HOME to java 11 or open project by Android Studio then config it.
- You can also install from https://install.appcenter.ms/users/trong.nguyen-axonactive.com/apps/news/distribution_groups/vsee/releases/1 
