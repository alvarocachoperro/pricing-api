package es.ecommerce.demo.infra.persistence;

import es.ecommerce.demo.infra.persistence.model.PriceJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceJPA, Long> {

    @Query("SELECT p FROM PriceJPA p WHERE p.productId = :productId AND p.id.brandId = :brandId AND " +
            ":applicationDate " +
            "BETWEEN p.id.startDate AND p.id.endDate ORDER BY p.priority DESC")
    List<PriceJPA> findTopBy(LocalDateTime applicationDate, int productId, int brandId);
}
