package woowahanyousbang.apply.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCurrecyRepository extends CurrencyRepository, JpaRepository<Currency, Long> {
}
