package es.ecommerce.demo.infra.db.springdata.repository;

import es.ecommerce.demo.app.repository.PriceRepository;
import es.ecommerce.demo.domain.Price;
import es.ecommerce.demo.infra.db.springdata.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PriceDBORepository implements PriceRepository {

    private final  PriceJpaRepository repository;

    private final PriceEntityMapper priceMapper;

    @Override
    public List<Price> findByDateProductAndBrand(LocalDateTime applicationDate, int productId, int brandId) {
        return repository.findByDateProductAndBrand(applicationDate, productId, brandId).stream().map(priceMapper::map).collect(
                Collectors.toCollection(ArrayList::new));
    }
}
