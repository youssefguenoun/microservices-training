package com.sfeir.school.microservices.stockservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by youssefguenoun on 16/06/2017.
 */
@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
public class Stock {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_seq")
    @SequenceGenerator(name = "stock_id_seq", sequenceName = "stock_id_seq")
    private Long id;

    @Column(name = "product_ref")
    @NotNull
    private String productRef;

    @Column(name = "quantity")
    private Integer quantity;

    public void decreaseQuantity(Integer quantityDelta) {
        this.quantity = this.quantity - quantityDelta;
    }

    public void increaseQuantity(Integer quantityDelta) {
        this.quantity = this.quantity + quantityDelta;
    }

}
