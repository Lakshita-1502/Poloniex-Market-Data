package com.example.poloniexmarketdata.service;

import com.example.poloniexmarketdata.model.MarketPrice;
import com.example.poloniexmarketdata.repository.MarketPriceRepository;
import com.example.poloniexmarketdata.model.MarkPrice;
import com.example.poloniexmarketdata.repository.MarkPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketDataService {

    private final MarketPriceRepository marketRepo;
    private final MarkPriceRepository markRepo;

    public MarketDataService(MarketPriceRepository marketRepo, MarkPriceRepository markRepo) {
        this.marketRepo = marketRepo;
        this.markRepo = markRepo;
    }

    public void saveMarketPrices(List<MarketPrice> marketPrices) {
        marketRepo.saveAll(marketPrices);
    }

    public List<MarketPrice> getMarketPrices() {
        return marketRepo.findAll();
    }

    public void saveMarkPrices(List<MarkPrice> markPrices){
        markRepo.saveAll(markPrices);
    }

    public List<MarkPrice> getMarkPrices() {
        return markRepo.findAll();
    }
}