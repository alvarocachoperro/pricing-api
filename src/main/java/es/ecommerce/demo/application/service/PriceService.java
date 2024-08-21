package es.ecommerce.demo.application.service;

import es.ecommerce.demo.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
