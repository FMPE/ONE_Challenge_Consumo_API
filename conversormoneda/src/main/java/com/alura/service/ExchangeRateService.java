package com.alura.service;

import com.alura.ExchangeRateResponse;
import com.alura.dto.ConversionResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class ExchangeRateService {
    private static final String API_KEY = "SUBE-TU-API-KEY";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private ExchangeRateResponse cachedRates;

    public void loadUSDRates() {
        this.cachedRates = getExchangeRates("USD");
        if (cachedRates == null || !"success".equalsIgnoreCase(cachedRates.result())) {
            throw new RuntimeException("No se pudieron cargar los tipos de cambio en el inicio");
        }
    }

    public ConversionResponse convert(String baseCurrency, String targetCurrency, double amount) {
        if (cachedRates == null) {
            throw new RuntimeException("Los tipos de cambio no están cargados");
        }

        Map<String, Double> rates = cachedRates.conversion_rates();

        if (baseCurrency.equals("USD")) {
            Double targetRate = rates.get(targetCurrency);
            if (targetRate == null) {
                throw new RuntimeException("Moneda de destino no soportada: " + targetCurrency);
            }
            double convertedAmount = amount * targetRate;
            return new ConversionResponse(baseCurrency, targetCurrency, amount, convertedAmount, targetRate);
        } else {
            Double baseRate = rates.get(baseCurrency);
            if (baseRate == null) {
                throw new RuntimeException("Moneda base no soportada: " + baseCurrency);
            }
            if (targetCurrency.equals("USD")) {
                double convertedAmount = amount / baseRate;
                double exchangeRate = 1.0 / baseRate;
                return new ConversionResponse(baseCurrency, targetCurrency, amount, convertedAmount, exchangeRate);
            } else {
                Double targetRate = rates.get(targetCurrency);
                if (targetRate == null) {
                    throw new RuntimeException("Moneda de destino no soportada: " + targetCurrency);
                }
                double convertedAmount = (amount / baseRate) * targetRate;
                double exchangeRate = targetRate / baseRate;
                return new ConversionResponse(baseCurrency, targetCurrency, amount, convertedAmount, exchangeRate);
            }
        }
    }

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
