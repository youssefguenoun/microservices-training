package com.sfeir.school.microservices.storeservice.service.client.sales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
/**
 * OrderInputDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-24T00:11:05.026+02:00")

public class OrderInputDTO   {
  @JsonProperty("customer")
  private String customer = null;

  @JsonProperty("orderDate")
  private ZonedDateTime orderDate = null;

  @JsonProperty("productRef")
  private String productRef = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  @JsonProperty("unitPrice")
  private BigDecimal unitPrice = null;

  public OrderInputDTO customer(String customer) {
    this.customer = customer;
    return this;
  }

   /**
   * Get customer
   * @return customer
  **/
  @ApiModelProperty(value = "")
  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public OrderInputDTO orderDate(ZonedDateTime orderDate) {
    this.orderDate = orderDate;
    return this;
  }

   /**
   * Get orderDate
   * @return orderDate
  **/
  @ApiModelProperty(value = "")
  public ZonedDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(ZonedDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public OrderInputDTO productRef(String productRef) {
    this.productRef = productRef;
    return this;
  }

   /**
   * Get productRef
   * @return productRef
  **/
  @ApiModelProperty(value = "")
  public String getProductRef() {
    return productRef;
  }

  public void setProductRef(String productRef) {
    this.productRef = productRef;
  }

  public OrderInputDTO quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public OrderInputDTO unitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
    return this;
  }

   /**
   * Get unitPrice
   * @return unitPrice
  **/
  @ApiModelProperty(value = "")
  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderInputDTO orderInputDTO = (OrderInputDTO) o;
    return Objects.equals(this.customer, orderInputDTO.customer) &&
        Objects.equals(this.orderDate, orderInputDTO.orderDate) &&
        Objects.equals(this.productRef, orderInputDTO.productRef) &&
        Objects.equals(this.quantity, orderInputDTO.quantity) &&
        Objects.equals(this.unitPrice, orderInputDTO.unitPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, orderDate, productRef, quantity, unitPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderInputDTO {\n");
    
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    orderDate: ").append(toIndentedString(orderDate)).append("\n");
    sb.append("    productRef: ").append(toIndentedString(productRef)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    unitPrice: ").append(toIndentedString(unitPrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

