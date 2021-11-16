package woowahanyousbang.apply.infra;

import woowahanyousbang.apply.domain.Currency;

import java.util.*;

public class InMemoryCurrencyRepository implements CurrencyRepository {
    private Map<UUID, Currency> currencyMap = new HashMap<>();

    @Override
    public Currency save(Currency currency) {
        currencyMap.put(currency.getId(), currency);
        return currency;
    }

    @Override
    public List<Currency> findAll() {
        return new ArrayList<>(currencyMap.values());
    }
}
