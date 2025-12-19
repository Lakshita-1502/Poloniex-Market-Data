package com.example.poloniexmarketdata.repository;

import com.example.poloniexmarketdata.model.MarketTicker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketTickerRepository extends MongoRepository<MarketTicker, String> {}
