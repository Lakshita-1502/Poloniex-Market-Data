package com.example.poloniexmarketdata.controller;

import com.example.poloniexmarketdata.model.MarkPrice;
import com.example.poloniexmarketdata.model.MarketPrice;
import com.example.poloniexmarketdata.service.MarketDataService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class PoloniexController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final MarketDataService service;

    public PoloniexController(MarketDataService service) {
        this.service = service;
    }

    @GetMapping("/fetch-market-prices")
    public String fetchMarketPrices() {

        String url = "https://api.poloniex.com/markets/price";

        ResponseEntity<List<MarketPrice>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MarketPrice>>() {}
                );

        List<MarketPrice> prices = response.getBody();

        service.saveMarketPrices(prices);

        return "Saved " + prices.size() + " Market Prices records to MongoDB";
    }

    @GetMapping("/fetch-mark-prices")
    public String fetchMarkPrices() {
        String url = "https://api.poloniex.com/markets/markPrice";

        ResponseEntity<List<MarkPrice>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<MarkPrice>>() {}
                );
        List<MarkPrice> markPrices = response.getBody();
        service.saveMarkPrices(markPrices);
        return "Saved " + markPrices.size() + " Mark Prices records to MongoDB";
    }
}