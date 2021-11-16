package woowahanyousbang.apply.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import woowahanyousbang.apply.domain.Currency;

import java.util.UUID;

public interface JpaCurrecyRepository extends CurrencyRepository, JpaRepository<Currency, UUID> {
}
