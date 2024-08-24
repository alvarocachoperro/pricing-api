package es.ecommerce.demo.app.service;

import es.ecommerce.demo.app.service.exception.PriceNotFoundException;
import es.ecommerce.demo.domain.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException;
}
