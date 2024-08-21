package es.ecommerce.demo.infrastructure.db.springdata.dbo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceJPA {

    @EmbeddedId
    private PriceIdJPA id;

    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "PRIORITY")
    private int priority;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "CURR")
    private String currency;

}
