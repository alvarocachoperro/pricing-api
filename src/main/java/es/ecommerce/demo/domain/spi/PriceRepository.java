package es.ecommerce.demo.domain.spi;

import es.ecommerce.demo.infra.persistence.model.PriceJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository {

    @Query("SELECT p FROM PriceJPA p WHERE p.id.productId = :productId AND p.id.brandId = :brandId AND " +
            ":applicationDate " +
            "BETWEEN p.id.startDate AND p.id.endDate ORDER BY p.priority DESC")
    Optional<PriceJPA> findApplicablePrice(LocalDateTime applicationDate, int productId, int brandId);
}
