package es.ecommerce.demo.infra.db.springdata.mapper;

import es.ecommerce.demo.domain.Price;
import es.ecommerce.demo.infra.db.springdata.dbo.PriceJPA;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PriceEntityMapper {

    @Mapping(target = "brandId", source = "id.brandId")
    @Mapping(target = "startDate", source = "id.startDate")
    @Mapping(target = "endDate", source = "id.endDate")
    @Mapping(target = "priceList", source = "id.priceList")
    Price map(PriceJPA priceEntity);
}
