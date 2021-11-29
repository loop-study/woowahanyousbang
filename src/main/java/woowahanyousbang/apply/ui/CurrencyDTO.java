package woowahanyousbang.apply.ui;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyDTO {
    private String name;
    private BigDecimal exchangeRate;
    private LocalDateTime dateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
