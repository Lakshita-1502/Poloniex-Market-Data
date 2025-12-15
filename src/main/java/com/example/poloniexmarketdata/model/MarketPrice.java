package com.example.poloniexmarketdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market_prices")
public class MarketPrice {

    @Id
    private String id;

    private String symbol;
    private String price;
    private String dailyChange;
    private long time;
    private long ts;

    // getters & setters

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDailyChange() {
        return dailyChange;
    }

    public void setDailyChange(String dailyChange) {
        this.dailyChange = dailyChange;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}