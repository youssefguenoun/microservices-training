package com.sfeir.school.microservices.salesservice.repository;

import com.sfeir.school.microservices.salesservice.domain.SalesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>, JpaSpecificationExecutor<SalesOrder> {

    Page<SalesOrder> findByCustomer(String customer, Pageable pageable);
}
