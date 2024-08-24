package es.ecommerce.demo.infra.db.springdata.repository;

import es.ecommerce.demo.infra.db.springdata.dbo.PriceJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceJPA, Long> {

    @Query("SELECT p FROM PriceJPA p WHERE p.productId = :productId AND p.id.brandId = :brandId AND " +
            ":applicationDate " +
            "BETWEEN p.id.startDate AND p.id.endDate")
    List<PriceJPA> findByDateProductAndBrand(LocalDateTime applicationDate, int productId, int brandId);

}
