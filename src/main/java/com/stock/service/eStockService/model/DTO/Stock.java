package com.stock.service.eStockService.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {


    private String companyCode;
    private Date timestamp;
    private Double stockPrice;

}
