package com.app.DTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal price;
    private Integer customerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    private BigDecimal totalPrice;
    private String status;
    private String productName;


}
