package woowahanyousbang.apply.ui;

import java.math.BigDecimal;

public class CurrencyForm {

    private String name;
    private BigDecimal exchangeRate;

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
}
