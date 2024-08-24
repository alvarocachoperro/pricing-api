package es.ecommerce.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int priceList;
    private Long productId;
    private int priority;
    private BigDecimal value;
    private String currency;
}
