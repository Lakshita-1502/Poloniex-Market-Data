package com.example.poloniexmarketdata.repository;

import com.example.poloniexmarketdata.model.MarkPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkPriceRepository
        extends MongoRepository<MarkPrice, String>{}