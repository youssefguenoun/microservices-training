package com.sfeir.school.microservices.stockservice.repository;

import com.sfeir.school.microservices.stockservice.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by youssefguenoun on 21/06/2017.
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findOneByProductRef(String productRef);
}
