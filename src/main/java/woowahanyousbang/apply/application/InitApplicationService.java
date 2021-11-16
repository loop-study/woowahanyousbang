package woowahanyousbang.apply.application;

import org.springframework.stereotype.Service;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.infra.CurrencyClient;
import woowahanyousbang.apply.infra.CurrencyRepository;
import woowahanyousbang.apply.ui.CurrencyDTO;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitApplicationService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyClient currencyClient;

    public InitApplicationService(CurrencyRepository currencyRepository,
                                  CurrencyClient currencyClient) {
        this.currencyRepository = currencyRepository;
        this.currencyClient = currencyClient;
    }

    @PostConstruct
    public void init() {
        List<CurrencyDTO> currencyFormList = currencyClient.currenciesInfo();

        currencyFormList.forEach(form
                -> currencyRepository.save(new Currency(form.getName(), form.getExchangeRate())));
    }
}
