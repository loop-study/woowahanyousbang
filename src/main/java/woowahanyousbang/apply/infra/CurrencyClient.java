package woowahanyousbang.apply.infra;

import woowahanyousbang.apply.ui.CurrencyDTO;

import java.util.List;

public interface CurrencyClient {
    List<CurrencyDTO> currenciesInfo();
}
