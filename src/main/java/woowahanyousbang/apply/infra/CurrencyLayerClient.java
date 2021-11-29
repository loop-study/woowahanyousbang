package woowahanyousbang.apply.infra;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import woowahanyousbang.apply.ui.CurrencyDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@EnableRetry
@Component
public class CurrencyLayerClient implements CurrencyClient {
    private static final String URL = "http://api.currencylayer.com/live?access_key=acef7b834bf5c7d8aff8fbc45a46db0a&currencies=KRW,JPY,PHP&format=1";
    private static final String FAIL_MESSAGE = "환율 정보 조회에 실패했습니다.";

    private final RestTemplate restTemplate;

    public CurrencyLayerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @Override
    @Retryable(
        maxAttempts = 3,
        backoff = @Backoff(3000)
    )
    public List<CurrencyDTO> currenciesInfo() throws Exception {
        CurrencyLayerDTO currencyLayerDTO = restTemplate.getForObject(URL, CurrencyLayerDTO.class);

        if (!currencyLayerDTO.isSuccess()) {
            throw new Exception(FAIL_MESSAGE);
        }

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
