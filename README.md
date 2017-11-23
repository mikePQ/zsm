# Uruchamianie:

### 1. Serwer

W katalogu części serwerowej:
> ./gradlew bootRun <br>

Serwer wystartuje na porcie 9090

### 2. Aplikacja mobilna

W pliku config.properties w src/main/assets należy ustawić adres serwera.

Następnie w kalogu aplikacji mobilnej:
> ./gradlew installDebug

Aplikacja zostanie uruchomiona na działającym emulatorze lub podłączonym urządzeniu
