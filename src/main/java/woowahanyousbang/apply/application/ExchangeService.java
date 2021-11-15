package woowahanyousbang.apply.application;

import org.springframework.stereotype.Service;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.domain.CurrencyRepository;
import woowahanyousbang.apply.ui.CurrencyForm;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {
    private final CurrencyRepository currencyRepository;

    public ExchangeService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyForm> findAll() {
        List<Currency> currencies = currencyRepository.findAll();

        return currencies.stream()
                .map(currency -> toCurrencyForm(currency))
                .collect(Collectors.toList());
    }

    private CurrencyForm toCurrencyForm(Currency currency) {
        CurrencyForm currencyForm = new CurrencyForm();
        currencyForm.setName(currency.getName());
        currencyForm.setExchangeRate(currency.getExchangeRate());
        currencyForm.setDateTime(currency.getDateTime());
        return currencyForm;
    }
}
