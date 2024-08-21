package es.ecommerce.demo.application.service.service.impl;

import es.ecommerce.demo.application.service.service.PriceService;
import es.ecommerce.demo.domain.Price;
import es.ecommerce.demo.application.repository.PriceRepository;
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
