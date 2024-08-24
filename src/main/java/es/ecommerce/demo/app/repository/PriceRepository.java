package es.ecommerce.demo.app.repository;

import es.ecommerce.demo.domain.Price;


import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {

    List<Price> findByDateProductAndBrand(LocalDateTime applicationDate, int productId, int brandId);
}
