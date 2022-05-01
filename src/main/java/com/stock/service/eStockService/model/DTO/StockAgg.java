package com.stock.service.eStockService.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockAgg {

    Float maxStockValue;
    Float minStockValue;
    Float avgStockValue;
}
