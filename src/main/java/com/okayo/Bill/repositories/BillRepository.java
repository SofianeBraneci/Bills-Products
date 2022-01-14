package com.okayo.Bill.repositories;

import com.okayo.Bill.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByClient(String client);

}
