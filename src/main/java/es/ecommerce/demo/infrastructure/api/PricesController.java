package es.ecommerce.demo.infrastructure.api;

import es.ecommerce.demo.application.service.service.PriceService;
import es.ecommerce.demo.domain.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class PricesController {
    private final PriceService priceService;

    @Autowired
    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/product-price")
    public Price getProductPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        return  priceService.getPrice(applicationDate, productId, brandId).orElse(null);
    }
}
