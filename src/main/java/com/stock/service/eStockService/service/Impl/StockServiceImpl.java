package com.stock.service.eStockService.service.Impl;

import com.stock.service.eStockService.mapper.SimpleSourceDestinationMapper;
import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.DTO.StockAgg;
import com.stock.service.eStockService.model.DTO.StockData;
import com.stock.service.eStockService.model.entity.MongoDBEntity.StockMongoEntity;
import com.stock.service.eStockService.repository.MongoDBRepository.StockMongoDBRepository;
import com.stock.service.eStockService.service.StockService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    StockMongoDBRepository stockEntityMongoRepository;

    private SimpleSourceDestinationMapper mapperImpl
            = Mappers.getMapper(SimpleSourceDestinationMapper.class);

    public Stock addStock(String companyCode, Double stockPrice){
        StockMongoEntity stockmongoEntity=StockMongoEntity.builder()
                .companyCode(companyCode)
                .stockPrice(stockPrice)
                .timestamp(new Date())
                .build();
        StockMongoEntity stockResult = stockEntityMongoRepository.insert(stockmongoEntity);
        return mapperImpl.EntityToStock(stockResult);
    }

    @Override
    public StockData getStockFromDate(String companyCode, Date fromDate, Date endDate) {
        List<StockMongoEntity> returnedList = stockEntityMongoRepository.findAllbyInterval(companyCode, fromDate, endDate);
        GroupOperation aggprice = group("$companyCode").max("$stockPrice").as("maxStockValue")
                .min("$stockPrice").as("minStockValue")
                .avg("$stockPrice").as("avgStockValue");
        MatchOperation filterCriteria = match(Criteria.where("companyCode").is(companyCode).and("timestamp").gte(fromDate).lte(endDate));
        TypedAggregation<StockMongoEntity> agg = newAggregation(StockMongoEntity.class,filterCriteria,
                aggprice);
        AggregationResults<StockAgg> result = mongoTemplate.aggregate(agg,"StockDetails",StockAgg.class);
        return StockData.builder().stock(mapperImpl.EntityListToStockList(returnedList)).stockAggr(result.getUniqueMappedResult()).build();

    }

    @Override
    public Stock getLatestStockDetail(String companycode) {
        StockMongoEntity result = stockEntityMongoRepository.findTopByCompanyCodeOrderByTimestampDesc(companycode);
        if(result!=null){
            return mapperImpl.EntityToStock(result);
        }
        return null;
    }

    @Override
    public List<Stock> deleteCompanyData(String companycode) {
        List<StockMongoEntity> deletedResults = mongoTemplate.findAllAndRemove(new Query(Criteria.where("companyCode").is(companycode)), StockMongoEntity.class);
        if(deletedResults!=null){
            return mapperImpl.EntityListToStockList(deletedResults);
        }
        return null;
    }

}
