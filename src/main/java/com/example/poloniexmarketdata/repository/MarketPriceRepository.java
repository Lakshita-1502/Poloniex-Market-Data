package com.example.poloniexmarketdata.repository;

import com.example.poloniexmarketdata.model.MarketPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketPriceRepository
        extends MongoRepository<MarketPrice, String> {
}