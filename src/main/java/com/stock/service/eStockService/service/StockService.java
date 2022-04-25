package com.stock.service.eStockService.service;

import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.DTO.StockData;

import java.util.Date;
import java.util.List;


public interface StockService {

    Stock addStock(String companyCode, Double stockPrice);

    StockData getStockFromDate(String companyCode, Date fromDate, Date endDate);
}
