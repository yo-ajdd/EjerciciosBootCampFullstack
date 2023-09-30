package com.campusdual.model.mapper;

import com.campusdual.model.Product;
import com.campusdual.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto toDTO(Product product);
    List<ProductDto> toDTOList(List<Product> products);
    Product toEntity(ProductDto productDto);
}
