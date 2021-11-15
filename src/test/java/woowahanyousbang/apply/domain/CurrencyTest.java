package woowahanyousbang.apply.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import woowahanyousbang.apply.ui.CurrencyForm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class CurrencyTest {

    @Test
    void 화폐_생성() {
        Currency currency = new Currency("KRW", BigDecimal.valueOf(1));

        assertThat(currency).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 화폐명_빈값_예외(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Currency(name, BigDecimal.valueOf(1111.11111)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"K","KW","KRWS"})
    void 화폐명_3글자_아니면_예외(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Currency(name, BigDecimal.valueOf(1111.11111)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -0.1, -11.0})
    void 환율_0_이하면_예외(Double exchangeRate) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Currency("KRW", BigDecimal.valueOf(exchangeRate)));
    }

    @ParameterizedTest
    @CsvSource(value = {"1.10,10,11.00", "0.10,10,1.00", "1111.00,10,11110.00"}, delimiter = ',')
    void 수취금액_계산(BigDecimal exchangeRate, BigDecimal remittance, BigDecimal receivable) {
        Currency currency = new Currency("KRW", exchangeRate);

        assertThat(currency.exchange(remittance)).isEqualTo(receivable);
    }
}

