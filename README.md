# Weather & Activity Planner (Java)

O aplicație de desktop dezvoltată în Java care preia date meteo în timp real folosind un API extern și oferă sugestii de activități (ex: fotbal sau studiu) în funcție de condițiile meteorologice.

## Caracteristici
- **Date în timp real:** Integrare cu [OpenWeatherMap API](https://openweathermap.org/api).
- **Modern Networking:** Utilizarea `HttpClient` din Java 11 pentru cereri asincrone/sincrone.
- **Parsing JSON:** Procesarea răspunsurilor de la server folosind librăria **Google Gson**.
- **Logică Personalizată:** Sistem de recomandări bazat pe temperatura și descrierea vremii.
- **Error Handling:** Gestionarea situațiilor de tip "Oraș negăsit" (HTTP 404) sau probleme de conexiune.

## Tehnologii Utilizate
- **Limbaj:** Java 11
- **Management Proiect:** Maven
- **Librării:** Google Gson (pentru JSON parsing)
- **API:** OpenWeatherMap
