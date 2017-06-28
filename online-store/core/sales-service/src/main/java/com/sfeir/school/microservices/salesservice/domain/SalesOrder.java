package com.sfeir.school.microservices.salesservice.domain;

import com.sfeir.school.microservices.util.domain.JSR310DateConverters.ZonedDateTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by youssefguenoun on 16/06/2017.
 */
@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class SalesOrder {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_id_seq")
    @SequenceGenerator(name = "sales_id_seq", sequenceName = "sales_id_seq")
    private Long id;

    @Column(name = "order_date")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime orderDate;

    @Column(name = "product_ref")
    @NotNull
    private String productRef;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;
}
