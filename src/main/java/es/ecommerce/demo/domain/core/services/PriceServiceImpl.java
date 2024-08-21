package es.ecommerce.demo.domain.core.services;

import es.ecommerce.demo.domain.api.PriceService;
import es.ecommerce.demo.domain.core.model.Price;
import es.ecommerce.demo.infra.persistence.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return Optional.ofNullable(
                priceRepository.findTopBy(applicationDate, productId.intValue(), brandId.intValue()).stream().findFirst().map(
                        priceJPA -> new Price((long)priceJPA.getProductId(), (long) priceJPA.getId().getBrandId(),
                                priceJPA.getId().getPriceList(),
                                priceJPA.getId().getStartDate(), priceJPA.getId().getEndDate(), priceJPA.getPrice())).orElse(null));
    }
}
