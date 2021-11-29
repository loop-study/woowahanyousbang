package woowahanyousbang.apply.application;

import org.springframework.stereotype.Service;
import woowahanyousbang.apply.domain.Currency;
import woowahanyousbang.apply.infra.CurrencyRepository;
import woowahanyousbang.apply.ui.CurrencyDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeService {
    private final CurrencyRepository currencyRepository;

    public ExchangeService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyDTO> findAll() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencies.stream()
                .map(currency -> toCurrencyForm(currency))
                .collect(Collectors.toList());
    }

    private CurrencyDTO toCurrencyForm(Currency currency) {
        CurrencyDTO currencyForm = new CurrencyDTO();
        currencyForm.setName(currency.getName());
        currencyForm.setExchangeRate(currency.getExchangeRate());
        currencyForm.setDateTime(currency.getDateTime());
        return currencyForm;
    }
}
