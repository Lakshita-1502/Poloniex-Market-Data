package com.example.poloniexmarketdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "mark_prices")
public class MarkPrice {

    @Id
    private String id;

    private String symbol;
    private BigDecimal markPrice;
    private long time;

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getMarkPrice() {
        return markPrice;
    }

    public long getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setMarkPrice(BigDecimal markPrice) {
        this.markPrice = markPrice;
    }

    public void setTime(long time) {
        this.time = time;
    }
}