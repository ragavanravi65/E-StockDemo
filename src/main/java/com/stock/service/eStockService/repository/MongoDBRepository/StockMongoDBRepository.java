package com.stock.service.eStockService.repository.MongoDBRepository;

import com.stock.service.eStockService.model.entity.MongoDBEntity.StockMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockMongoDBRepository extends MongoRepository<StockMongoEntity,String>{
    @Query(value="{$and: [" +
            "{companyCode: ?0}," +
            "{$and: [" +
            "{timestamp:" +
            "{ $gte:?1, $lte:?2 }" +
            "}]" +
            "}" +
            "]}")
    List<StockMongoEntity> findAllbyInterval(String companyCD,Date fromDate,Date endDate);


    StockMongoEntity findTopByCompanyCodeOrderByTimestampDesc(String companycode);

}
