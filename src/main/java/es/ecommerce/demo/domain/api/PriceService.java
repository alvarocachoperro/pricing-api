package es.ecommerce.demo.domain.api;

import es.ecommerce.demo.domain.core.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
