package woowahanyousbang.apply.infra;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import woowahanyousbang.apply.domain.CurrencyLayer;
import woowahanyousbang.apply.ui.CurrencyForm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ApiCurrencyLayer implements CurrencyLayer {
    private static final String url = "http://api.currencylayer.com/live?access_key=acef7b834bf5c7d8aff8fbc45a46db0a&currencies=KRW,JPY,PHP&format=1";

    private final RestTemplate restTemplate;

    public ApiCurrencyLayer(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<CurrencyForm> currenciesInfo() {
        CurrencyLayerForm currencyLayerForm = restTemplate.getForObject(url, CurrencyLayerForm.class);
        return toCurrencyForm(currencyLayerForm);
    }

    private List<CurrencyForm> toCurrencyForm(CurrencyLayerForm currencyLayerForm) {
        List<CurrencyForm> result = new ArrayList<>();
        Map currencyMap = currencyLayerForm.getQuotes();

        currencyMap.keySet().forEach(key -> {
            CurrencyForm currencyForm = new CurrencyForm();
            currencyForm.setName(changeName(key.toString()));
            currencyForm.setExchangeRate(BigDecimal.valueOf((Double)currencyMap.get(key)));

            result.add(currencyForm);
        });

        return result;
    }

    private String changeName(String name) {
        return name.replace("USD", "");
    }
}
