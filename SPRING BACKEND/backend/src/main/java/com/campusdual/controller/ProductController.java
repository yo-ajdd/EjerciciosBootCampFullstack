package com.campusdual.controller;

import com.campusdual.api.IProductService;
import com.campusdual.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(value = "/test")
    public String testController() {
        return "Hola!!!!!";
    }

    @PostMapping(value = "/test")
    public String testController(@RequestBody String name) {
        return "Hola " + name;
    }

    @PostMapping(value="/get")
    public ProductDto queryProduct(@RequestBody ProductDto productDto) {
        try {
            return productService.queryProduct(productDto);
        } catch (EntityNotFoundException e) {
            return new ProductDto();
        }
    }
    @GetMapping(value="/getAll")
    public List<ProductDto> queryAllProducts() {
        return productService.queryAllProducts();
    }

    @PostMapping("/add")
    public int addProduct(@RequestBody ProductDto productDto) {
        return productService.insertProduct(productDto);
    }

    @PutMapping("/update")
    public int updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/delete")
    public int deleteProduct(@RequestBody ProductDto productDto) {
        return productService.deleteProduct(productDto);
    }

    @PostMapping("/getname")
    public List<ProductDto> queryProductName(@RequestBody ProductDto productDto){
        return productService.queryProductsByName(productDto);
    }

    @PostMapping("/getstock")
    public List<ProductDto> queryProductsByStock(@RequestBody ProductDto productDto){
        return productService.queryProductsByStock(productDto);
    }

    @GetMapping("/getactive")
    public List<ProductDto> queryProductsByActive(){
        return productService.queryProductsByActive();
    }

    @GetMapping("/getstockpositive")
    public List<ProductDto> queryProductsByStockGreaterThan(){
        return productService.queryProductsByStockGreaterThan();
    }



}
