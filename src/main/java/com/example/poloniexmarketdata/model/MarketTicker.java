package com.example.poloniexmarketdata.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "market_tickers")
public class MarketTicker {

    @Id
    private String id;

    @Indexed(unique = true)
    private String symbol;

    private String dailyChange;
    private BigDecimal high;
    private String amount;
    private String quantity;
    private Integer tradeCount;
    private BigDecimal low;
    private Long closeTime;
    private Long startTime;
    private BigDecimal close;
    private String open;
    private Long ts;
    private String markPrice;

    public String getDailyChange() {
        return dailyChange;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getAmount() {
        return amount;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public Integer getTradeCount() {
        return tradeCount;
    }

    public BigDecimal getLow() {
        return low;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public BigDecimal getClose() {
        return close;
    }

    public String getOpen() {
        return open;
    }

    public Long getTs() {
        return ts;
    }

    public String getMarkPrice() {
        return markPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarkPrice(String markPrice) {
        this.markPrice = markPrice;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public void setTradeCount(Integer tradeCount) {
        this.tradeCount = tradeCount;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public void setDailyChange(String dailyChange) {
        this.dailyChange = dailyChange;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
