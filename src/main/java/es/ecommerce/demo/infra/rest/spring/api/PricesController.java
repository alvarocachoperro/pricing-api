package es.ecommerce.demo.infra.rest.spring.api;

import es.ecommerce.demo.app.service.PriceService;
import es.ecommerce.demo.app.service.exception.PriceNotFoundException;
import es.ecommerce.demo.infra.rest.spring.dto.PriceDto;
import es.ecommerce.demo.infra.rest.spring.mapper.PriceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class PricesController {
    private final PriceService priceService;
    private final PriceDtoMapper mapper;

    @GetMapping("/product-price")
    public PriceDto getProductPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) throws PriceNotFoundException {
        return mapper.map(priceService.getPrice(applicationDate, productId, brandId));
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> handlePriceNotFound(PriceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidArguments(IllegalArgumentException ex) {
        return new ResponseEntity<>("Error:" + ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }
}
