package com.alura.controller;

import com.alura.dto.ConversionRequest;
import com.alura.dto.ConversionResponse;
import com.alura.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class CurrencyController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping
    public ResponseEntity<ConversionResponse> convert(@RequestBody ConversionRequest request) {
        try {
            ConversionResponse response = exchangeRateService.convert(
                    request.baseCurrency(),
                    request.targetCurrency(),
                    request.amount()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
