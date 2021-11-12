package woowahanyousbang.apply.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

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
}

