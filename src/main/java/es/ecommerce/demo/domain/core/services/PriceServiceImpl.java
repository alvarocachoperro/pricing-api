package es.ecommerce.demo.domain.core.services;

import es.ecommerce.demo.domain.api.PriceService;
import es.ecommerce.demo.domain.core.model.Price;
import es.ecommerce.demo.domain.spi.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<Price> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return Optional.ofNullable(
                priceRepository.findApplicablePrice(applicationDate, productId.intValue(), brandId.intValue()).map(
                        priceJPA -> new Price((long)priceJPA.getProductId(), (long) priceJPA.getId().getBrandId(),
                                priceJPA.getId().getPriceList(),
                                priceJPA.getId().getStartDate(), priceJPA.getId().getEndDate(), priceJPA.getPrice())).orElse(null));
    }
}
