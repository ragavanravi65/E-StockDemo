package com.stock.service.eStockService.repository;

import com.stock.service.eStockService.model.DTO.StockStats;
import com.stock.service.eStockService.model.entity.StockKey;
import com.stock.service.eStockService.model.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, StockKey> {
    @Query(value = "SELECT * FROM STOCK_ENTITY s WHERE" +
            " s.Company_Code=:companyCD AND s.timestamp>=:fromDate and s.timestamp<=:endDate",nativeQuery = true)
    List<StockEntity> findAllbyInterval(@Param("companyCD") String companyCD,@Param("fromDate") Date fromDate,@Param("endDate") Date endDate);

    @Query(value = "SELECT max(s.stock_price) as maxStockValue,min(s.stock_price) as minStockValue,avg(s.stock_price) as avgStockValue FROM STOCK_ENTITY s WHERE" +
            " s.Company_Code=:companyCD AND s.timestamp>=:fromDate and s.timestamp<=:endDate",nativeQuery = true)
    StockStats getStatsForInterval(@Param("companyCD") String companyCD,@Param("fromDate") Date fromDate,@Param("endDate") Date endDate);


}
