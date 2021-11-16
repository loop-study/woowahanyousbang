package woowahanyousbang.apply.infra;

import woowahanyousbang.apply.domain.Currency;

import java.util.List;

public interface CurrencyRepository {
    Currency save(Currency currency);

    List<Currency> findAll();
}
