package com.okayo.Bill.services;

import com.okayo.Bill.models.Bill;
import com.okayo.Bill.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getBills(){
        return billRepository.findAll();
    }

    public List<Bill> getBillsOf(String client){
        return billRepository.findByClient(client);

    }

    public Bill addBill(Bill bill){
        return billRepository.save(bill);

    }

    public Bill findById(Long id){
        return billRepository.findById(id).orElse(new Bill());
    }

    public void deleteBill(Long id){
        billRepository.deleteById(id);
    }

}
