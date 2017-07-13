package com.sfeir.school.microservices.salesservice.repository.specifications;

import com.sfeir.school.microservices.salesservice.domain.SalesOrder;
import com.sfeir.school.microservices.salesservice.domain.SalesOrder_;
import org.springframework.data.jpa.domain.Specification;

import static org.springframework.data.jpa.domain.Specifications.where;
import static org.springframework.util.StringUtils.hasText;

/**
 * Created by youssefguenoun on 20/06/2017.
 */
public final class SalesOrderSpecificationBuilder {

    private final SalesOrderSearchFilter salesOrderSearchFilter;

    public SalesOrderSpecificationBuilder(SalesOrderSearchFilter salesOrderSearchFilter) {
        this.salesOrderSearchFilter = salesOrderSearchFilter;
    }

    public Specification<SalesOrder> hasProductReference() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(SalesOrder_.productRef), salesOrderSearchFilter.getProduct());
    }

    public Specification<SalesOrder> hasOrderDateBetween() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get(SalesOrder_.orderDate), salesOrderSearchFilter.getFromDate(), salesOrderSearchFilter.getToDate());
    }

    public Specification<SalesOrder> hasOrderDateLessThan() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(SalesOrder_.orderDate), salesOrderSearchFilter.getToDate());
    }

    public Specification<SalesOrder> hasOrderDateGreaterThan() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(SalesOrder_.orderDate), salesOrderSearchFilter.getFromDate());
    }

    public Specification<SalesOrder> buildSalesOrderSpecification() {
        if (hasText(salesOrderSearchFilter.getProduct()) && (null != salesOrderSearchFilter.getFromDate()) && (null != salesOrderSearchFilter.getToDate())) {
            return where(hasProductReference()).and(hasOrderDateBetween());
        } else if (hasText(salesOrderSearchFilter.getProduct()) && (null == salesOrderSearchFilter.getFromDate()) && (null != salesOrderSearchFilter.getToDate())) {
            return where(hasProductReference()).and(hasOrderDateLessThan());
        } else if (hasText(salesOrderSearchFilter.getProduct()) && (null != salesOrderSearchFilter.getFromDate()) && (null == salesOrderSearchFilter.getToDate())) {
            return where(hasProductReference()).and(hasOrderDateGreaterThan());
        } else if (hasText(salesOrderSearchFilter.getProduct()) && (null == salesOrderSearchFilter.getFromDate()) && (null == salesOrderSearchFilter.getToDate())) {
            return where(hasProductReference());
        } else if (!hasText(salesOrderSearchFilter.getProduct()) && (null != salesOrderSearchFilter.getFromDate()) && (null != salesOrderSearchFilter.getToDate())) {
            return where(hasOrderDateBetween());
        } else if (!hasText(salesOrderSearchFilter.getProduct()) && (null == salesOrderSearchFilter.getFromDate()) && (null != salesOrderSearchFilter.getToDate())) {
            return where(hasOrderDateLessThan());
        } else if (!hasText(salesOrderSearchFilter.getProduct()) && (null != salesOrderSearchFilter.getFromDate()) && (null == salesOrderSearchFilter.getToDate())) {
            return where(hasOrderDateGreaterThan());
        } else {
            return null;
        }
    }
}
