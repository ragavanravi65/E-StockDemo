package com.stock.service.eStockService.model.DTO;



//projection
public interface StockStats{
    Float getMaxStockValue();
    Float getMinStackValue();
    Float getAvgStockValue();

}