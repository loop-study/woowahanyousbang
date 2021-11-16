package woowahanyousbang.apply.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.infra.CurrencyRepository;
import woowahanyousbang.apply.infra.InMemoryCurrencyRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ExchangeServiceTest {
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setUp() {
        currencyRepository = new InMemoryCurrencyRepository();
    }

    @Test
    void 환율_저장() {
        Currency currency = createCurrency("KRW", 1111.11d);

        Currency saveCurrency = currencyRepository.save(currency);

        assertThat(currency).isEqualTo(saveCurrency);
    }

    @Test
    void 환율_조회() {
        Currency currency = createCurrency("KRW", 1111.11d);
        Currency currency2 = createCurrency("JPY", 999.99d);

        currencyRepository.save(currency);
        currencyRepository.save(currency2);

        List<Currency> currencies = currencyRepository.findAll();

        assertThat(currencies.size()).isEqualTo(2);
        assertThat(currencies.size()).isGreaterThan(1);
        assertThat(currencies).contains(currency, currency2);
    }



    private Currency createCurrency(String name, Double exchangeRate) {
        return new Currency(name, BigDecimal.valueOf(exchangeRate));
    }
}