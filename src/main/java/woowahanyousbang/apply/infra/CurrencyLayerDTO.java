package woowahanyousbang.apply.infra;

import java.math.BigDecimal;
import java.util.Map;

public class CurrencyLayerDTO {
    private boolean success;
    private String source;
    private Map<String, BigDecimal> quotes;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map getQuotes() {
        return quotes;
    }

    public void setQuotes(Map quotes) {
        this.quotes = quotes;
    }
}
