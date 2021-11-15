package woowahanyousbang.apply.application;

import org.springframework.stereotype.Service;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.domain.CurrencyLayer;
import woowahanyousbang.apply.domain.CurrencyRepository;
import woowahanyousbang.apply.ui.CurrencyForm;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitApplicationService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyLayer currencyLayer;

    public InitApplicationService(CurrencyRepository currencyRepository,
                                  CurrencyLayer currencyLayer) {
        this.currencyRepository = currencyRepository;
        this.currencyLayer = currencyLayer;
    }

    @PostConstruct
    public void init() {
        List<CurrencyForm> currencyFormList = currencyLayer.currenciesInfo();

        currencyFormList.forEach(form
                -> currencyRepository.save(new Currency(form.getName(), form.getExchangeRate())));
    }
}
