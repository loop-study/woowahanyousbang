package woowahanyousbang.apply.domain;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {
    Currency save(Currency currency);

    Optional<Currency> findById(Long id);

    List<Currency> findAll();
}
