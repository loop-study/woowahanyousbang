package woowahanyousbang.apply.infra;

import java.util.Map;

public class CurrencyLayerForm {
    private boolean success;
    private String source;
    private Map quotes;

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
