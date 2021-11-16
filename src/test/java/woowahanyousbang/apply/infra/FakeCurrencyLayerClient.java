package woowahanyousbang.apply.infra;

import woowahanyousbang.apply.ui.CurrencyDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FakeCurrencyLayerClient implements CurrencyClient {

    @Override
    public List<CurrencyDTO> currenciesInfo() {
        List<CurrencyDTO> result = new ArrayList<>();
        result.add(create("KRW", BigDecimal.valueOf(1234.11)));
        result.add(create("PHP", BigDecimal.valueOf(999.99)));
        result.add(create("JPY", BigDecimal.valueOf(111.11)));

        return result;
    }

    private CurrencyDTO create(String name, BigDecimal exchangeRate) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setExchangeRate(exchangeRate);
        currencyDTO.setName(name);
        return currencyDTO;
    }
}
