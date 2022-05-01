package com.stock.service.eStockService.model.entity.MongoDBEntity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("StockDetails")
public class StockMongoEntity {
    private String id;
    private String companyCode;
    private Date timestamp;

    private Double stockPrice;
}
