package woowahanyousbang.apply.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Currency {
    private static String NAME_NULL_AND_EMPTY_ERROR_MESSAGE = "화폐명은 비울 수 없습니다.";
    private static String NAME_SIZE_ERROR_MESSAGE = "화폐명은 3글자입니다.";
    private static String EXCHANGE_RATE_ERROR_MESSAGE = "환율은 0보다 커야합니다.";

    protected Currency() { }

    @Id
    @Column(name = "id", columnDefinition = "varbinary(16)")
    private UUID id;

    private String name;

    private BigDecimal exchangeRate;

    private LocalDateTime dateTime;

    public Currency(String name, BigDecimal exchangeRate) {
        validationName(name);
        validationExchangeRate(exchangeRate);
        this.id = UUID.randomUUID();
        this.name = name;
        this.exchangeRate = exchangeRate;
        this.dateTime = LocalDateTime.now();
    }

    public BigDecimal exchange(BigDecimal remittance){
        return exchangeRate.multiply(remittance);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(getName(), currency.getName()) && Objects.equals(getExchangeRate(), currency.getExchangeRate()) && Objects.equals(getDateTime(), currency.getDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExchangeRate(), getDateTime());
    }
}
