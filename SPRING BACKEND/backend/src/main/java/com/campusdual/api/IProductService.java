package com.campusdual.api;

import com.campusdual.model.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto queryProduct(ProductDto productDto);
    List<ProductDto> queryAllProducts();
    List<ProductDto> queryProductsByName(ProductDto productDto);
    List<ProductDto> queryProductsByStock(ProductDto productDto);
    List<ProductDto> queryProductsByActive();
    List<ProductDto> queryProductsByStockGreaterThan();
    int insertProduct(ProductDto productDto);
    int updateProduct(ProductDto productDto);
    int deleteProduct(ProductDto productDto);



}
