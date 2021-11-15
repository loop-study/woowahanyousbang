package woowahanyousbang.apply.ui;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import woowahanyousbang.apply.application.ExchangeService;

import java.util.List;

@Controller
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/")
    public String exchange(Model model) {
        List<CurrencyForm> currencies = exchangeService.findAll();
        model.addAttribute("currencies", currencies);
        return "exchange";
    }
}
