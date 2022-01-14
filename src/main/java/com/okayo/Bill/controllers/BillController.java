package com.okayo.Bill.controllers;

import com.okayo.Bill.models.Bill;
import com.okayo.Bill.models.Product;
import com.okayo.Bill.services.BillService;
import com.okayo.Bill.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bills")
public class BillController {

    private BillService billService;
    private ProductService productService;

    @Autowired
    public BillController(BillService billService, ProductService productService) {
        this.billService = billService;
        this.productService = productService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getBills(){
        List<Bill> bills = billService.getBills();
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/find")
    public ResponseEntity<Bill> findBillById(@RequestParam(name = "billId") Long id){

        Bill bill = billService.findById(id);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/find-products")
    public ResponseEntity<Set<Product>> findBillProducts(@RequestParam(name = "billId") Long id){

        Bill bill = billService.findById(id);
        return ResponseEntity.ok(bill.getProducts());
    }

    @PutMapping("/add")
    public ResponseEntity<Bill> addBill(@RequestParam(name = "emitter") String emitter,
                                        @RequestParam(name = "client") String client){
        Bill bill = new Bill(emitter, client);
        billService.addBill(bill);
        return ResponseEntity.ok(bill);
    }

    @PutMapping("/add-product")
    public ResponseEntity<Bill> addProductToBill(@RequestParam(name = "billId") Long billId,
                                              @RequestParam(name = "productId") Long productId){
        Bill bill = billService.findById(billId);
        Product product = productService.findById(productId);
        bill.addProduct(product);
        billService.addBill(bill);
        return ResponseEntity.ok(bill);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteBill(@RequestParam(name = "billId") Long billId){
        billService.deleteBill(billId);
        return ResponseEntity.ok(billId);

    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Long> deleteProductFromBill(@RequestParam(name = "billId") Long billId ,
                                                      @RequestParam(name = "productId") Long productId){
        Bill bill = billService.findById(billId);
        Product product = productService.findById(productId);
        bill.getProducts().remove(product);
        billService.addBill(bill);
        return ResponseEntity.ok(productId);

    }


}
