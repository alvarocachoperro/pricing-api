package es.ecommerce.demo.app.service.exception;


import lombok.Getter;

@Getter
public class PriceNotFoundException extends Exception {
    public PriceNotFoundException(String message) {
        super(message);
    }
}
