package com.alura;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {
    // Reemplaza 'YOUR-API-KEY' con tu clave de API real de ExchangeRate-API
    private static final String API_KEY = "YOUR-API-KEY";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ExchangeRateResponse getExchangeRates(String baseCurrency) {
        URI uri = URI.create(BASE_URL + API_KEY + "/latest/" + baseCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), ExchangeRateResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener los tipos de cambio", e);
        }
    }
}
