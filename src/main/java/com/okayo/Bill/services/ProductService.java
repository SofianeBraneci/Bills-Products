package com.okayo.Bill.services;


import com.okayo.Bill.models.Product;
import com.okayo.Bill.repositories.ProductRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private final int PAGE_SIZE = 10;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProducts(int page){
      return  productRepository.findAll(PageRequest.of(page, PAGE_SIZE)).toList();
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public void addProducts(MultipartFile file) throws IOException {

        String [] headers = {"product_name", "price", "tva"};
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(headers)
                .withSkipHeaderRecord()
                .parse(new InputStreamReader(file.getInputStream()));

        List<Product> products = new ArrayList<>();
        for(CSVRecord record : records){
            Product product = new Product(record.get("product_name"),
                    Double.valueOf(record.get("price")),
                    Double.valueOf(record.get("tva")));
            products.add(product);
        }

        productRepository.saveAll(products);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(new Product());
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);

    }


}
