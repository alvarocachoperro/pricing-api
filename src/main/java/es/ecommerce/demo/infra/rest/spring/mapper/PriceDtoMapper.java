package es.ecommerce.demo.infra.rest.spring.mapper;


import es.ecommerce.demo.domain.Price;
import es.ecommerce.demo.infra.rest.spring.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceDtoMapper {
    PriceDto map(Price from);
}
