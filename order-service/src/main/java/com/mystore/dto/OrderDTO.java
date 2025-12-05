package com.mystore.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

	@NotNull
    private BigDecimal totalAmount;

	@NotNull
    private List<OrderDetailDTO> details;
}
