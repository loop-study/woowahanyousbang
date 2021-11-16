package woowahanyousbang.apply.infra;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import woowahanyousbang.apply.ui.CurrencyDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyLayerClient implements CurrencyClient {
    private static final String url = "http://api.currencylayer.com/live?access_key=acef7b834bf5c7d8aff8fbc45a46db0a&currencies=KRW,JPY,PHP&format=1";

    private final RestTemplate restTemplate;

    public CurrencyLayerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<CurrencyDTO> currenciesInfo() {
        CurrencyLayerDTO currencyLayerDTO = restTemplate.getForObject(url, CurrencyLayerDTO.class);
        return toCurrencyForm(currencyLayerDTO);
    }

    private List<CurrencyDTO> toCurrencyForm(CurrencyLayerDTO currencyLayerForm) {
        List<CurrencyDTO> result = new ArrayList<>();
        Map currencyMap = currencyLayerForm.getQuotes();

        currencyMap.keySet().forEach(key -> {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setName(changeName(key.toString()));
            currencyDTO.setExchangeRate(BigDecimal.valueOf((Double)currencyMap.get(key)));

            result.add(currencyDTO);
        });

        return result;
    }

    private String changeName(String name) {
        return name.replace("USD", "");
    }
}
