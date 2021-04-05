package de.slimou.mobilshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShopController {

    private MobilService mobilService;

    public ShopController(MobilService mobilService) {
        this.mobilService = mobilService;
    }

    @GetMapping(path = "/home")
    public String homepage() {
        return "index";
    }



}
