package com.stock.service.eStockService.mapper;


import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.DTO.StockMongoTemp;
import com.stock.service.eStockService.model.entity.MongoDBEntity.StockMongoEntity;
import com.stock.service.eStockService.model.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SimpleSourceDestinationMapper {
    Stock EntityToStock(StockMongoEntity stockEntity);
    List<Stock> EntityListToStockList(List<StockMongoEntity> stockEntity);
    Stock StockEntityToStock(StockEntity stockEntity);
    StockMongoTemp EntityToTemp(StockMongoEntity stockEntity);
    StockMongoEntity TempToEntity(StockMongoTemp stockEntity);
}