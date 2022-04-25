package com.stock.service.eStockService.service.Impl;

import com.stock.service.eStockService.mapper.SimpleSourceDestinationMapper;
import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.DTO.StockData;
import com.stock.service.eStockService.model.DTO.StockStats;
import com.stock.service.eStockService.model.entity.StockEntity;
import com.stock.service.eStockService.repository.StockRepository;
import com.stock.service.eStockService.service.StockService;
import com.stock.service.eStockService.util.TimeUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository stockRepository;

    private SimpleSourceDestinationMapper mapperImpl
            = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    public Stock addStock(String companyCode, Double stockPrice){
        StockEntity se=StockEntity.builder()
                .companyCode(companyCode)
                .stockPrice(stockPrice)
                .build();
        StockEntity stockvalue = stockRepository.save(se);
        return mapperImpl.EntityToStock(stockvalue);
    }

    @Override
    public StockData getStockFromDate(String companyCode, Date fromDate, Date endDate) {
        List<StockEntity> returnedList = stockRepository.findAllbyInterval(companyCode, fromDate, endDate);
        StockStats stockData = stockRepository.getStatsForInterval(companyCode, fromDate, endDate);
        return StockData.builder().stock(mapperImpl.EntityListToStockList(returnedList)).stockStats(stockData).build();

    }

}
