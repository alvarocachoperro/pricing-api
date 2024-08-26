package es.ecommerce.demo.infra.rest.spring.api;

import es.ecommerce.demo.app.service.PriceService;
import es.ecommerce.demo.app.service.exception.PriceNotFoundException;
import es.ecommerce.demo.infra.rest.spring.dto.PriceDto;
import es.ecommerce.demo.infra.rest.spring.exception.ErrorResponse;
import es.ecommerce.demo.infra.rest.spring.mapper.PriceDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handlePriceNotFound(PriceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidArguments(IllegalArgumentException ex) {
        String error = "Error:" + ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        String error = String.format("Parameter '%s' is missing", name);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
