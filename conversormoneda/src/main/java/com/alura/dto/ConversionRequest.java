package com.alura.dto;

public record ConversionRequest(String baseCurrency, String targetCurrency, double amount) {
}
