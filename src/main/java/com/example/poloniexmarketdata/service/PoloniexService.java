package com.example.poloniexmarketdata.service;

import com.example.poloniexmarketdata.model.MarketTicker;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class PoloniexService {

    private final MongoTemplate mongoTemplate;

    public PoloniexService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void upsert(MarketTicker ticker) {
        Query query = new Query(Criteria.where("symbol").is(ticker.getSymbol()));

        Update update = new Update()
                .set("symbol", ticker.getSymbol())
                .set("dailyChange", ticker.getDailyChange())
                .set("high", ticker.getHigh())
                .set("amount", ticker.getAmount())
                .set("quantity", ticker.getQuantity())
                .set("tradeCount", ticker.getTradeCount())
                .set("low", ticker.getLow())
                .set("closeTime", ticker.getCloseTime())
                .set("startTime", ticker.getStartTime())
                .set("close", ticker.getClose())
                .set("open", ticker.getOpen())
                .set("ts", ticker.getTs())
                .set("markPrice", ticker.getMarkPrice());

        // UPSERT: update if exists, insert if not
        mongoTemplate.upsert(query, update, MarketTicker.class);

        System.out.println("UPSERTED ticker for symbol: " + ticker.getSymbol());
    }
}
