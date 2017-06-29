package com.sfeir.school.microservices.storeservice.service.client.stock.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * StockViewDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-23T20:31:03.250+02:00")

public class StockViewDTO   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("productRef")
  private String productRef = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

  public StockViewDTO id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StockViewDTO productRef(String productRef) {
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

  public StockViewDTO quantity(Integer quantity) {
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
    StockViewDTO stockViewDTO = (StockViewDTO) o;
    return Objects.equals(this.id, stockViewDTO.id) &&
        Objects.equals(this.productRef, stockViewDTO.productRef) &&
        Objects.equals(this.quantity, stockViewDTO.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productRef, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StockViewDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

