package com.okayo.Bill.controllers;

import com.okayo.Bill.models.Product;
import com.okayo.Bill.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/upload")
    public ResponseEntity handleUpload(@RequestParam(name= "file")MultipartFile file){
        try {
            productService.addProducts(file);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("File uploaded unsuccessful");
        }


    }

    @GetMapping("/all")
    public  ResponseEntity<List<Product>> getProducts(@RequestParam(name = "page") Optional<Integer> page){
        List<Product> products;
        if(page.isPresent()) {
           products = productService.getProducts(page.get());
        }else{
            products = productService.getProducts();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/find")
    public  ResponseEntity<Product> findProduct(@RequestParam(name = "id") Long id){
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestParam(name = "name") String productName,
                           @RequestParam(name = "price") Double price,
                           @RequestParam(name = "tva") Double tva){
        Product product = new Product(productName, price, tva);
        productService.addProduct(product);

        return ResponseEntity.ok(product.getProductId());

    }


    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteProduct(@RequestParam(name = "id") Long id){

        productService.deleteProduct(id);
        return ResponseEntity.ok(id);

    }



}
