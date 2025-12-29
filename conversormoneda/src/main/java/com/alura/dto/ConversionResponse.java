package com.alura.dto;

public record ConversionResponse(String baseCurrency, String targetCurrency, double originalAmount, double convertedAmount, double exchangeRate) {
}
