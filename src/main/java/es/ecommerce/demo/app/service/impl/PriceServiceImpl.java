package es.ecommerce.demo.app.service.impl;

import es.ecommerce.demo.app.service.PriceService;
import es.ecommerce.demo.app.service.exception.IErrorMessages;
import es.ecommerce.demo.app.service.exception.PriceNotFoundException;
import es.ecommerce.demo.domain.Price;
import es.ecommerce.demo.app.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId) throws PriceNotFoundException {
        return priceRepository.findByDateProductAndBrand(applicationDate, productId.intValue(), brandId.intValue()).stream().max(
                Comparator.comparingInt(Price::getPriority)).orElseThrow(()-> new PriceNotFoundException(IErrorMessages.ERROR_NOTFOUND));
    }
}
