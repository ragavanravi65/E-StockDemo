package com.stock.service.eStockService.model.entity;

import java.io.Serializable;
import java.util.Date;

public class StockKey implements Serializable {
    private String companyCode;
    private Date timestamp;
    private Double stockPrice;

}
