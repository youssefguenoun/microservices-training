package com.sfeir.school.microservices.salesservice.service.client.stock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * StockInputDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-23T20:31:03.250+02:00")

public class StockInputDTO   {
  @JsonProperty("productRef")
  private String productRef = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  public StockInputDTO productRef(String productRef) {
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

  public StockInputDTO quantity(Integer quantity) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StockInputDTO stockInputDTO = (StockInputDTO) o;
    return Objects.equals(this.productRef, stockInputDTO.productRef) &&
        Objects.equals(this.quantity, stockInputDTO.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productRef, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StockInputDTO {\n");
    
    sb.append("    productRef: ").append(toIndentedString(productRef)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

