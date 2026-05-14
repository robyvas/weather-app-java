package ro.robert;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherApp {

    private static final String API_KEY = "227caa4d2f295f373dd9efcb074daa30"; 
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdu orașul: ");
        String city = scanner.nextLine();

        try {
            String response = fetchWeatherData(city);
            parseAndDisplay(response);
        } catch (Exception e) {
            System.out.println("Eroare: Nu am putut prelua datele. Verifică orașul sau conexiunea.");
        }
        
        scanner.close();
    }

    private static String fetchWeatherData(String city) throws Exception {
        String url = String.format(BASE_URL, city, API_KEY);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Verificăm codul de status (foarte important pentru interviu!)
        if (response.statusCode() == 404) throw new Exception("Oraș negăsit");
        
        return response.body();
    }

    private static void parseAndDisplay(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        
        String cityName = jsonObject.get("name").getAsString();
        double temp = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
        String description = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        System.out.println("\n--- Vremea în " + cityName + " ---");
        System.out.printf("Temperatură: %.1f°C\n", temp);
        System.out.println("Stare: " + description.toUpperCase());
        
        if (temp > 20 && !description.contains("rain")) {
            System.out.println("Sfat: E vreme perfectă de un fotbal în aer liber!");
        } else {
            System.out.println("Sfat: Mai bine rămâi în casă și lucrezi la proiecte.");
        }
    }
}