package com.stock.service.eStockService.mapper;


import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SimpleSourceDestinationMapper {
    Stock EntityToStock(StockEntity stockEntity);

    List<Stock> EntityListToStockList(List<StockEntity> stockEntity);
}