package com.campusdual.service;

import com.campusdual.api.IProductService;
import com.campusdual.model.Product;
import com.campusdual.model.dao.ProductDao;
import com.campusdual.model.dto.ProductDto;
import com.campusdual.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService")
@Lazy
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDto queryProduct(ProductDto productDto) {
        // paso el dto a producto
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        // Llamo al dao para que haga la consulta
        return ProductMapper.INSTANCE.toDTO(productDao.getReferenceById(product.getId()));
    }

    @Override
    public List<ProductDto> queryAllProducts() {
        List<Product> productList = productDao.findAll();
        List<ProductDto> result = ProductMapper.INSTANCE.toDTOList(productList);
        return result;
    }

    @Override
    public List<ProductDto> queryProductsByName(ProductDto productDto) {
        String name = productDto.getName();
        if (!(name.contains("%"))) {
            name = "%" + name + "%";
        }
        List<Product> productList = productDao.findByNameLike(name);
        return ProductMapper.INSTANCE.toDTOList(productList);
    }

    @Override
    public List<ProductDto> queryProductsByStock(ProductDto productDto) {
        Integer stock = productDto.getStock();
        List<Product> productList = productDao.findByStock(stock);
        return ProductMapper.INSTANCE.toDTOList(productList);
    }

    @Override
    public List<ProductDto> queryProductsByActive() {
        List<Product> productList = productDao.findByActiveTrue();
        return ProductMapper.INSTANCE.toDTOList(productList);
    }

    @Override
    public List<ProductDto> queryProductsByStockGreaterThan() {
        List<Product> productList = productDao.findByStockGreaterThan(0);
        return ProductMapper.INSTANCE.toDTOList(productList);
    }

    @Override
    public int insertProduct(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        productDao.saveAndFlush(product);
        return product.getId();
    }

    @Override
    public int updateProduct(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        productDao.saveAndFlush(product);
        return product.getId();
    }

    @Override
    public int deleteProduct(ProductDto productDto) {
        int id = productDto.getId();
        Product product = new Product();
        product.setId(id);
        productDao.delete(product);
        return id;
    }
}
