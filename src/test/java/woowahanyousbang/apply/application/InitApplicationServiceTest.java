package woowahanyousbang.apply.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.infra.CurrencyClient;
import woowahanyousbang.apply.infra.CurrencyRepository;
import woowahanyousbang.apply.infra.FakeCurrencyLayerClient;
import woowahanyousbang.apply.infra.InMemoryCurrencyRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InitApplicationServiceTest {
    private CurrencyClient currencyClient;
    private CurrencyRepository currencyRepository;
    private InitApplicationService initApplicationService;

    @BeforeEach
    void setUp() {
        currencyClient = new FakeCurrencyLayerClient();
        currencyRepository = new InMemoryCurrencyRepository();
        initApplicationService = new InitApplicationService(currencyRepository, currencyClient);
    }

    @Test
    void 외부API_환율정보_가져오기() {
        initApplicationService.init();

        List<Currency> currencies = currencyRepository.findAll();

        assertThat(currencies).isNotNull();
        assertThat(currencies.size()).isGreaterThan(1);
    }
}