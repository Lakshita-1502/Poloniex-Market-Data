package com.example.poloniexmarketdata.websocket;

import com.example.poloniexmarketdata.model.MarketTicker;
import com.example.poloniexmarketdata.service.PoloniexService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.math.BigDecimal;

@Component
public class PoloniexWebSocketListener {

    private static final String WS_URL = "wss://ws.poloniex.com/ws/public";

    private final PoloniexService poloniexService;
    private final ObjectMapper mapper = new ObjectMapper();

    public PoloniexWebSocketListener(PoloniexService poloniexService) {
        this.poloniexService = poloniexService;
    }

    @PostConstruct
    public void connect() {

        StandardWebSocketClient client = new StandardWebSocketClient();

        client.doHandshake(new TextWebSocketHandler() {

            @Override
            public void afterConnectionEstablished(
                    org.springframework.web.socket.WebSocketSession session) throws Exception {

                String subscribeMessage = """
                {
                  "event": "subscribe",
                  "channel": ["ticker"],
                  "symbols": ["BTC_USDT", "ETH_USDT"]
                }
                """;

                session.sendMessage(new TextMessage(subscribeMessage));
                System.out.println("Subscribed to Poloniex ticker channel");
            }

            @Override
            protected void handleTextMessage(
                    org.springframework.web.socket.WebSocketSession session,
                    TextMessage message) throws Exception {

                System.out.println("RAW WS MESSAGE: " + message.getPayload());

                JsonNode root = mapper.readTree(message.getPayload());

                // Ignore subscribe / error messages
                if (root.has("event") || !root.has("data")) {
                    return;
                }

                JsonNode dataArray = root.get("data");
                if (!dataArray.isArray()) {
                    return;
                }

                for (JsonNode data : dataArray) {

                    MarketTicker ticker = new MarketTicker();

                    ticker.setSymbol(text(data, "symbol"));
                    ticker.setDailyChange(text(data, "dailyChange"));
                    ticker.setAmount(text(data, "amount"));
                    ticker.setQuantity(text(data, "quantity"));
                    ticker.setTradeCount(intVal(data, "tradeCount"));
                    ticker.setOpen(text(data, "open"));
                    ticker.setTs(longVal(data, "ts"));

                    // BigDecimal fields
                    ticker.setHigh(decimal(data, "high"));
                    ticker.setLow(decimal(data, "low"));
                    ticker.setClose(decimal(data, "close"));

                    poloniexService.upsert(ticker);
                }
            }

        }, WS_URL);
    }

    // ---------- SAFE PARSERS ----------

    private String text(JsonNode node, String field) {
        return node.hasNonNull(field) ? node.get(field).asText() : null;
    }

    private int intVal(JsonNode node, String field) {
        return node.hasNonNull(field) ? node.get(field).asInt() : 0;
    }

    private long longVal(JsonNode node, String field) {
        return node.hasNonNull(field) ? node.get(field).asLong() : 0L;
    }

    private BigDecimal decimal(JsonNode node, String field) {
        return node.hasNonNull(field)
                ? new BigDecimal(node.get(field).asText())
                : BigDecimal.ZERO;
    }
}
