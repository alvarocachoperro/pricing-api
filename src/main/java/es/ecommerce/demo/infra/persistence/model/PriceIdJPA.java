package es.ecommerce.demo.infra.persistence.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceIdJPA implements Serializable {

    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceIdJPA priceIdJPA = (PriceIdJPA) o;
        return brandId == priceIdJPA.brandId &&
                priceList == priceIdJPA.priceList &&
                Objects.equals(startDate, priceIdJPA.startDate) &&
                Objects.equals(endDate, priceIdJPA.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, startDate, endDate, priceList);
    }
}
