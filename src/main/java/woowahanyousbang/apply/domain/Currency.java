package woowahanyousbang.apply.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Currency {
    private static String NAME_NULL_AND_EMPTY_ERROR_MESSAGE = "화폐명은 비울 수 없습니다.";
    private static String NAME_SIZE_ERROR_MESSAGE = "화폐명은 3글자입니다.";
    private static String EXCHANGE_RATE_ERROR_MESSAGE = "환율은 0보다 커야합니다.";

    private String name;
    private BigDecimal exchangeRate;

    Currency(String name, BigDecimal exchangeRate) {
        validationName(name);
        validationExchangeRate(exchangeRate);
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    private void validationExchangeRate(BigDecimal exchangeRate) {
        if (exchangeRate.compareTo(BigDecimal.ZERO) != 1) {
            throw new IllegalArgumentException(EXCHANGE_RATE_ERROR_MESSAGE);
        }
    }

    private void validationName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException(NAME_NULL_AND_EMPTY_ERROR_MESSAGE);
        }

        if (name.length() != 3) {
            throw new IllegalArgumentException(NAME_SIZE_ERROR_MESSAGE);
        }
    }
}
