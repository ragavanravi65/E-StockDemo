package com.stock.service.eStockService.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMongoTemp implements Serializable {

    private String id;
    private String companyCode;
    private Date timestamp;
    private Double stockPrice;
}
