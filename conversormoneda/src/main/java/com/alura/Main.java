package com.alura;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateService service = new ExchangeRateService();

        while (true) {
            System.out.println("*************************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("*************************************************");

            int option;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            if (option == 7) {
                System.out.println("Saliendo...");
                break;
            }

            String baseCurrency = "";
            String targetCurrency = "";

            switch (option) {
                case 1:
                    baseCurrency = "USD";
                    targetCurrency = "ARS";
                    break;
                case 2:
                    baseCurrency = "ARS";
                    targetCurrency = "USD";
                    break;
                case 3:
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case 4:
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "USD";
                    targetCurrency = "COP";
                    break;
                case 6:
                    baseCurrency = "COP";
                    targetCurrency = "USD";
                    break;
                default:
                    System.out.println("Opción no válida");
                    continue;
            }

            System.out.println("Ingrese el valor que deseas convertir:");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un monto válido.");
                continue;
            }

            try {
                ExchangeRateResponse response = service.getExchangeRates(baseCurrency);
                if (response != null && "success".equalsIgnoreCase(response.result())) {
                    Double rate = response.conversion_rates().get(targetCurrency);
                    
                    if (rate != null) {
                        double result = amount * rate;
                        System.out.printf("El valor %.1f [%s] corresponde al valor final de =>>> %.2f [%s]%n", amount, baseCurrency, result, targetCurrency);
                    } else {
                        System.out.println("No se encontró la tasa de cambio para " + targetCurrency);
                    }
                } else {
                    System.out.println("Error al obtener las tasas de cambio. Verifique su API Key o la conexión.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}